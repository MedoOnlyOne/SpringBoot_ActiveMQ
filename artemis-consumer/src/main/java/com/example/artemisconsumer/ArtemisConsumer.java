package com.example.artemisconsumer;

import com.example.artemisconsumer.models.*;
import com.example.artemisconsumer.repositpries.ApiAuditRepository;
import com.example.artemisconsumer.repositpries.ApiDumpRepository;
import com.example.artemisconsumer.services.Insert;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
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
    private ApiDumpRepository apiDumpRepository;
    @Autowired
    private ApiAuditRepository apiAuditRepository;

    @Autowired
    private Insert insert;

    private static final int MAX_LISTS = 3;
    private final int batch_size = 5;
    private final int MAX_THREADS = 3;

    private static Queue<List<ApiAuditEntity>> apiAuditEntityLists = new LinkedList<>();
    private static Queue<List<ApiDumpEntity>> apiDumpEntityLists = new LinkedList<>();
    private static boolean isListsAvailable;
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
        ArtemisConsumer.isListsAvailable = true;
        for (int i = 0; i < MAX_LISTS; i++){
            apiAuditEntityLists.add(new ArrayList<ApiAuditEntity>());
            apiDumpEntityLists.add(new ArrayList<ApiDumpEntity>());
        }
    }
    @JmsListener(destination = "${jms.queue.destination}" )
    public void receive(String msg , @Header(JmsHeaders.MESSAGE_ID) String messageId 
    		//@Header("CodedCharSetId") int CodedCharSetId , @Header("Encoding") int Encoding    		
    		) throws IOException, DecoderException {
        if(ArtemisConsumer.apiAuditEntityLists.peek() != null){
            if(ArtemisConsumer.apiAuditEntityLists.peek().size() == 0){
                ArtemisConsumer.t1 = new Timestamp(new Date().getTime());
                ArtemisConsumer.isInserted = false;
            }
            if(!ArtemisConsumer.isListsAvailable){
                // Save file content to DB
                saveFiles();
            }
        } else {
            if(ArtemisConsumer.isListsAvailable){
                ArtemisConsumer.writeToFiles();
            }
        }
        //System.out.println( messageId);
        //System.out.println( CodedCharSetId);
        //System.out.println( Encoding);
        //System.out.print("XML MESSAGE " + msg);
        ArtemisConsumer.mapAndSaveMsg(msg,messageId);

        if (ArtemisConsumer.isListsAvailable && ArtemisConsumer.apiAuditEntityLists.peek().size() >= batch_size) {
            ArtemisConsumer.t2 = new Timestamp(new Date().getTime());
            System.out.println("start reading "+ t1);
            System.out.println("end reading " +  t2);
            System.out.println("Read " + batch_size + " messages in " + (t2.getTime() - t1.getTime())/1000.0 + " s");
            System.out.println("List number: " + (MAX_LISTS - ArtemisConsumer.apiDumpEntityLists.size()));
            RunnableObject taskThread=new RunnableObject(insert,
                     apiDumpRepository,
                     apiAuditRepository,
                     ArtemisConsumer.apiDumpEntityLists.poll(),
                     ArtemisConsumer.apiAuditEntityLists.poll()
            );
            executor.execute(taskThread);
            ArtemisConsumer.isInserted = true;
            ArtemisConsumer.t1 = new Timestamp(new Date().getTime());
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

    public static boolean isIsListsAvailable() {
        return isListsAvailable;
    }

    public static String getDumpFileUnderProcessing() {
        return dumpFileUnderProcessing;
    }

    public static void setDumpFileUnderProcessing(String dumpFileUnderProcessing) {
        ArtemisConsumer.dumpFileUnderProcessing = dumpFileUnderProcessing;
    }

    public static String getAuditFileUnderProcessing() {
        return auditFileUnderProcessing;
    }

    public static void setAuditFileUnderProcessing(String auditFileUnderProcessing) {
        ArtemisConsumer.auditFileUnderProcessing = auditFileUnderProcessing;
    }

    public static void setIsListsAvailable(boolean isListsAvailable) {
        ArtemisConsumer.isListsAvailable = isListsAvailable;
    }

    public static String getAuditFileName() {
        return auditFileName;
    }

    public static void setAuditFileName(String auditFileName) {
        ArtemisConsumer.auditFileName = auditFileName;
    }

    public static String getDumpFileName() {
        return dumpFileName;
    }

    public static void setDumpFileName(String dumpFileName) {
        ArtemisConsumer.dumpFileName = dumpFileName;
    }

    public static void setFreeLists(List<ApiAuditEntity> apiAuditEntityList, List<ApiDumpEntity> apiDumpEntityList) {
        apiAuditEntityList.clear();
        apiAuditEntityLists.add(apiAuditEntityList);
        apiDumpEntityList.clear();
        apiDumpEntityLists.add(apiDumpEntityList);
    }

    private static void mapAndSaveMsg(String msg , String messageId) throws IOException , DecoderException{
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
        if (ArtemisConsumer.isListsAvailable){
            ArtemisConsumer.apiAuditEntityLists.peek().add(apiAuditEntity);
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
            
            if(ArtemisConsumer.isListsAvailable){
                ArtemisConsumer.apiDumpEntityLists.peek().add(apiDumpEntity);
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
        ArtemisConsumer.isListsAvailable = false;
        ArtemisConsumer.auditFileName = "Audit " + new Timestamp(new Date().getTime()).toString() + ".txt";
        ArtemisConsumer.dumpFileName = "Dump " + new Timestamp(new Date().getTime()).toString() + ".txt";
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

    private void saveFiles() throws IOException {
        ArtemisConsumer.isListsAvailable = true;
        System.out.println("Save files");
        List<ApiDumpEntity> apiDumpList = saveDumpFiles(ArtemisConsumer.dumpFileName, ArtemisConsumer.apiDumpEntityLists.poll());
        List<ApiAuditEntity> apiAuditList = saveAuditFiles(ArtemisConsumer.auditFileName, ArtemisConsumer.apiAuditEntityLists.poll());
        insert.insert(apiDumpRepository, apiAuditRepository, apiDumpList, apiAuditList);
        ArtemisConsumer.setFreeLists(apiAuditList, apiDumpList);
        ArtemisConsumer.dumpFileOutputStream.close();
        ArtemisConsumer.auditFileOutputStream.close();
        ArtemisConsumer.dumpFile.delete();
        ArtemisConsumer.auditFile.delete();
    }

    public static List<ApiDumpEntity> saveDumpFiles(String dumpFileName, List<ApiDumpEntity> apiDumpList){
        XmlMapper xmlMapper = new XmlMapper();
        String record = "";
        try {
            if (!dumpFileName.equals(ArtemisConsumer.dumpFileUnderProcessing)){
                ArtemisConsumer.dumpFileUnderProcessing = dumpFileName;
                List<String> dumpLines = Files.readAllLines(Paths.get(dumpFileName));
                System.out.println("Dump " + dumpLines.size());
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
            return apiDumpList;
    }

    public static List<ApiAuditEntity> saveAuditFiles(String auditFileName, List<ApiAuditEntity> apiAuditList){
        XmlMapper xmlMapper = new XmlMapper();
        String record = "";
        try {
            if (!auditFileName.equals(ArtemisConsumer.auditFileUnderProcessing)){
                ArtemisConsumer.auditFileUnderProcessing = auditFileName;
                List<String> auditLines = Files.readAllLines(Paths.get(auditFileName));
                System.out.println("Audit " + auditLines.size());

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
            return apiAuditList;
    }
}
