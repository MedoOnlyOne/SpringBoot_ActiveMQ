package com.example.artemisconsumer.services;

import com.example.artemisconsumer.models.*;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.config.JmsListenerEndpointRegistry;
import org.springframework.jms.support.JmsHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Component
public class ArtemisConsumer  {
    @Autowired
    private Insert insert;
    @Autowired
    JmsListenerEndpointRegistry endpointRegistry;
    @Autowired
    ArtemisDLQ dlqProducer;

    public static boolean isDLQ;
    private static final int MAX_LISTS = 3;
    private final int batch_size = 5;
    private final int MAX_THREADS = 2;

    private static Queue<List<ApiAuditEntity>> apiAuditEntityLists = new LinkedList<>();
    private static Queue<List<ApiDumpEntity>> apiDumpEntityLists = new LinkedList<>();
    private static Queue<List<String>> messageLists = new LinkedList<>();
    private static List<ApiAuditEntity> activeApiAuditList;
    private static List<ApiDumpEntity> activeApiDumpList;
    private static List<String> activeMessageList;
    private static boolean isWriteToFiles;
    private static String auditFileName;
    private static String dumpFileName;
    private static String dumpFileUnderProcessing;
    private static String auditFileUnderProcessing;
    public static File auditFile;
    public static File dumpFile;
    private static FileOutputStream auditFileOutputStream;
    private static FileOutputStream dumpFileOutputStream;

    private ExecutorService executor = Executors.newFixedThreadPool(MAX_THREADS);
    private static Timestamp t1, t2;
    private static boolean isInserted;

    static {
        ArtemisConsumer.t1 = new Timestamp(new Date().getTime());
        ArtemisConsumer.isInserted = true;
        ArtemisConsumer.isWriteToFiles = false;
        ArtemisConsumer.isDLQ = false;
        for (int i = 0; i < MAX_LISTS; i++){
            apiAuditEntityLists.add(new ArrayList<>());
            apiDumpEntityLists.add(new ArrayList<>());
            messageLists.add(new ArrayList<>());
        }
        activeApiAuditList = apiAuditEntityLists.poll();
        activeApiDumpList = apiDumpEntityLists.poll();
        activeMessageList = messageLists.poll();
    }
    @JmsListener(destination = "${jms.queue.destination}")
    public void receive(String msg , @Header(JmsHeaders.MESSAGE_ID) String messageId
    		//@Header("CodedCharSetId") int CodedCharSetId , @Header("Encoding") int Encoding    		
    		) throws IOException, DecoderException {
        if(activeApiDumpList != null && activeApiAuditList != null && activeApiAuditList.size() == 0){
            ArtemisConsumer.t1 = new Timestamp(new Date().getTime());
            ArtemisConsumer.isInserted = false;
        } else if(!ArtemisConsumer.isListsAvailable() && activeApiDumpList == null && activeApiAuditList == null
                && !isWriteToFiles){
//            shutdown();
//            ArtemisConsumer.writeToFiles();
            dlqProducer.sendDLQ(msg);

        } else if(ArtemisConsumer.isListsAvailable() && activeApiDumpList == null && activeApiAuditList == null){
            activeApiDumpList = apiDumpEntityLists.poll();
            activeApiAuditList = apiAuditEntityLists.poll();
//            if (isWriteToFiles){
//                ArtemisConsumer.saveFiles();
//            }
        }
        ArtemisConsumer.mapAndSaveMsg(msg,messageId, activeApiAuditList, activeApiDumpList);
        if(activeApiAuditList != null && activeApiDumpList != null && activeApiAuditList.size() >= batch_size){
            ArtemisConsumer.t2 = new Timestamp(new Date().getTime());
            System.out.println("start reading "+ t1);
            System.out.println("end reading " +  t2);
            System.out.println("Read " + batch_size + " messages in " + (t2.getTime() - t1.getTime())/1000.0 + " s");
            System.out.println("List number: " + (MAX_LISTS - ArtemisConsumer.apiDumpEntityLists.size()));
            RunnableObject taskThread=new RunnableObject(insert,
                    activeApiDumpList,
                    activeApiAuditList,
                    activeMessageList
            );
            executor.execute(taskThread);
            ArtemisConsumer.isInserted = true;
            activeApiAuditList = apiAuditEntityLists.poll();
            activeApiDumpList = apiDumpEntityLists.poll();
            activeMessageList = messageLists.poll();
        }
    }

    public static boolean isInsert() {
        return ArtemisConsumer.isInserted;
    }

    public static void setInsert(boolean insert) {
        ArtemisConsumer.isInserted = insert;
    }

    public static Timestamp getT1() {
        return ArtemisConsumer.t1;
    }

    public static Queue<List<ApiAuditEntity>> getApiAuditEntityLists() {
        return apiAuditEntityLists;
    }

    public static Queue<List<ApiDumpEntity>> getApiDumpEntityLists() {
        return apiDumpEntityLists;
    }

    public static boolean isListsAvailable() {
        return ((ArtemisConsumer.apiAuditEntityLists.size() > 0) && (ArtemisConsumer.apiDumpEntityLists.size() > 0));
    }

    public static boolean getIsWriteToFiles() {
        return isWriteToFiles;
    }

    public static void setIsWriteToFiles(boolean isWriteToFiles) {
        ArtemisConsumer.isWriteToFiles = isWriteToFiles;
    }

    public static List<ApiAuditEntity> getActiveApiAuditList() {
        List<ApiAuditEntity> returnedList = activeApiAuditList;
        activeApiAuditList  = null;
        return returnedList;
    }

    public static List<ApiDumpEntity> getActiveApiDumpList() {
        List<ApiDumpEntity> returnedList = activeApiDumpList;
        activeApiDumpList  = null;
        return returnedList;
    }

    public static List<String > getActiveMessageList() {
        List<String> returnedList = activeMessageList;
        activeMessageList  = null;
        return returnedList;
    }

    public static FileOutputStream getAuditFileOutputStream() {
        return auditFileOutputStream;
    }

    public static FileOutputStream getDumpFileOutputStream() {
        return dumpFileOutputStream;
    }

    public static void setFreeLists(List<ApiAuditEntity> apiAuditEntityList, List<ApiDumpEntity> apiDumpEntityList,
                                    List<String> messageList) {
        apiAuditEntityList.clear();
        apiAuditEntityLists.add(apiAuditEntityList);
        apiDumpEntityList.clear();
        apiDumpEntityLists.add(apiDumpEntityList);
        messageList.clear();
        messageLists.add(messageList);
    }

    private static void mapAndSaveMsg(String msg,
                                      String messageId,
                                      List<ApiAuditEntity> auditList,
                                      List<ApiDumpEntity> dumpList)
            throws IOException , DecoderException{

        activeMessageList.add(msg);
        XmlMapper xmlMapper = new XmlMapper();

        String MsgId = "<MsgId>" + messageId.split("ID:")[1] + "</MsgId>";
//        System.out.println(MsgId);
        String ReqId = "\n\t<ReqID>"+msg.split("<ReqID>")[1].split("</ReqID>")[0] + "</ReqID>";
        String ApiDetails =msg.split("<AuditRecord>")[1].split("</AuditRecord>")[0].split("<APIDetails>")[1].split("</APIDetails>")[0];
        String AuditVars = msg.split("<AuditRecord>")[1].split("</AuditRecord>")[0].split("<AuditVars>")[1].split("</AuditVars>")[0];

        String ApiName = "<APIName>" + ApiDetails.split("<APIName>")[1].split("</APIName>")[0] + "</APIName>";
        String ApiDump =  ApiName + "<APIRoot>" + ApiDetails.split("<APIRoot>")[1].split("</APIPath>")[0]+"</APIPath>";

        String AuditEntity = "<Audit>" + MsgId + ReqId + ApiDetails + AuditVars + "</Audit>\n\n";


        //Map Audit Entity
        ApiAuditEntity apiAuditEntity = xmlMapper.readValue(AuditEntity.getBytes(), ApiAuditEntity.class);
        if (!ArtemisConsumer.isWriteToFiles){
            auditList.add(apiAuditEntity);
        } else {
            // write to a file
            ArtemisConsumer.auditFileOutputStream.write(AuditEntity.getBytes(StandardCharsets.UTF_8));
        }

        //Map Dump Records
        String [] DumpRecords = msg.split("<DumpRecords>")[1].split("</DumpRecords>")[0].split("<Msg>");

        for (int i = 1; i < DumpRecords.length; i++) {
            String DumpRecord = "<Dump>" + MsgId + ReqId + "\n\t" + ApiDump +DumpRecords[i].split("</Msg>")[0] + "</Dump>\n\n";
        	//System.out.print(DumpRecord);
            ApiDumpEntity apiDumpEntity = xmlMapper.readValue(DumpRecord.getBytes(), ApiDumpEntity.class);
            decodePayload(apiDumpEntity);
//            System.out.println(apiDumpEntity.getMdPayload());
            
            if(!ArtemisConsumer.isWriteToFiles){
                dumpList.add(apiDumpEntity);
            } else {
                // write to a file
                ArtemisConsumer.dumpFileOutputStream.write(DumpRecord.getBytes(StandardCharsets.UTF_8));
            }
        }
    }
    private static void decodePayload(ApiDumpEntity apiDumpEntity) throws DecoderException {
        //	DECODING PAYLOAD
        String HexPayload = apiDumpEntity.getMdPayload();
        byte[] bytes = Hex.decodeHex(HexPayload.toCharArray());
        apiDumpEntity.setMdPayload(new String(bytes));
    }
    private static void writeToFiles() throws IOException {
        ArtemisConsumer.isWriteToFiles = true;
        ArtemisConsumer.auditFileName = "Audit " + new Timestamp(new Date().getTime()).toString() + ".txt ...................";
        ArtemisConsumer.dumpFileName = "Dump " + new Timestamp(new Date().getTime()).toString() + ".txt .....................";
        ArtemisConsumer.auditFileName = ArtemisConsumer.auditFileName.replaceAll(":", "-");
        ArtemisConsumer.dumpFileName = ArtemisConsumer.dumpFileName.replaceAll(":", "-");
        System.out.println(ArtemisConsumer.auditFileName);
        System.out.println(ArtemisConsumer.dumpFileName);
        ArtemisConsumer.auditFile = new File(ArtemisConsumer.auditFileName);
        ArtemisConsumer.dumpFile = new File(ArtemisConsumer.dumpFileName);
        ArtemisConsumer.auditFile.createNewFile();
        ArtemisConsumer.dumpFile.createNewFile();
        ArtemisConsumer.auditFileOutputStream = new FileOutputStream(ArtemisConsumer.auditFile);
        ArtemisConsumer.dumpFileOutputStream = new FileOutputStream(ArtemisConsumer.dumpFile);
    }

    private static void saveFiles() throws IOException {
        ArtemisConsumer.isWriteToFiles = false;
        ArtemisConsumer.dumpFileOutputStream.close();
        ArtemisConsumer.auditFileOutputStream.close();
        System.out.println("Save files......................");
        saveDumpFiles(ArtemisConsumer.dumpFileName, activeApiDumpList);
        saveAuditFiles(ArtemisConsumer.auditFileName, activeApiAuditList);
        ArtemisConsumer.dumpFile.delete();
        ArtemisConsumer.auditFile.delete();
    }

    public static void saveDumpFiles(String dumpFileName, List<ApiDumpEntity> apiDumpList){
        XmlMapper xmlMapper = new XmlMapper();
        String record = "";
        try {
            if (!dumpFileName.equals(ArtemisConsumer.dumpFileUnderProcessing)){
                ArtemisConsumer.dumpFileUnderProcessing = dumpFileName;
                List<String> dumpLines = Files.readAllLines(Paths.get(dumpFileName));
                for (String line : dumpLines) {
                    record += line;
                    if(line.contains("</Dump>")){
    //                    System.out.println("Dump record: " + record);
                        ApiDumpEntity apiDumpEntity = xmlMapper.readValue(record.getBytes(), ApiDumpEntity.class);
                        decodePayload(apiDumpEntity);
                        apiDumpList.add(apiDumpEntity);
                        record = "";
                    }
                }
                ArtemisConsumer.dumpFileUnderProcessing = "";
            }
        } catch (IOException | DecoderException e) {
            e.printStackTrace();
        }
        System.out.println("Dump file has " + apiDumpList.size() + " records ................");
    }

    public static void saveAuditFiles(String auditFileName, List<ApiAuditEntity> apiAuditList){
        XmlMapper xmlMapper = new XmlMapper();
        String record = "";
        try {
            if (!auditFileName.equals(ArtemisConsumer.auditFileUnderProcessing)){
                ArtemisConsumer.auditFileUnderProcessing = auditFileName;
                List<String> auditLines = Files.readAllLines(Paths.get(auditFileName));
                for (String line : auditLines) {
                    record += line;
                    if(line.contains("</Audit>")){
    //                    System.out.println("Audit record: " + record);
                        ApiAuditEntity apiAuditEntity = xmlMapper.readValue(record.getBytes(), ApiAuditEntity.class);
                        apiAuditList.add(apiAuditEntity);
                        record = "";
                    }
                }
                ArtemisConsumer.auditFileUnderProcessing = "";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Audit file has " + apiAuditList.size() + " records ..............");
    }

    private void shutdown() {
        endpointRegistry.getListenerContainers().forEach((container) -> {
            if (container.isRunning()) {
                System.out.println(" =======================> Shutting down listener: " + container.getDestinationResolver().toString());
                container.stop();
            }
        });
    }
}
