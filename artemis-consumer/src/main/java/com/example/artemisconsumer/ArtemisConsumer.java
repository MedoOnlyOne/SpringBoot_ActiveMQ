package com.example.artemisconsumer;

import com.example.artemisconsumer.models.*;
import com.example.artemisconsumer.repositpries.ApiAuditRepository;
import com.example.artemisconsumer.repositpries.ApiDumpRepository;
import com.example.artemisconsumer.services.Insert;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
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

    private static final int MAX_LISTS = 10;
    private final int batch_size = 500;
    private final int MAX_THREADS = 3;

    private static Queue<List<ApiAuditEntity>> apiAuditEntityLists = new LinkedList<>();
    private static Queue<List<ApiDumpEntity>> apiDumpEntityLists = new LinkedList<>();

    private ExecutorService executor = Executors.newFixedThreadPool(MAX_THREADS);
    private static Timestamp t1, t2;
    private static boolean isInserted;

    static {
        ArtemisConsumer.t1 = new Timestamp(new Date().getTime());
        ArtemisConsumer.isInserted = true;
        for (int i = 0; i < MAX_LISTS; i++){
            apiAuditEntityLists.add(new ArrayList<ApiAuditEntity>());
            apiDumpEntityLists.add(new ArrayList<ApiDumpEntity>());
        }
    }
    @JmsListener(destination = "${jms.queue.destination}")
    public void receive(String msg) throws IOException {
        if(ArtemisConsumer.apiAuditEntityLists.peek() != null && ArtemisConsumer.apiAuditEntityLists.peek().size() == 0){
            ArtemisConsumer.t1 = new Timestamp(new Date().getTime());
            ArtemisConsumer.isInserted = false;
        }

//        System.out.print("XML MESSAGE " + msg);
        
        XmlMapper xmlMapper = new XmlMapper();
        
        String ReqId = "<ReqID>"+msg.split("<ReqID>")[1].split("</ReqID>")[0] + "</ReqID>";
        String ApiDetails =msg.split("<AuditRecord>")[1].split("</AuditRecord>")[0].split("<APIDetails>")[1].split("</APIDetails>")[0];
        String AuditVars = msg.split("<AuditRecord>")[1].split("</AuditRecord>")[0].split("<AuditVars>")[1].split("</AuditVars>")[0];
        
        String ApiName = "<APIName>" + ApiDetails.split("<APIName>")[1].split("</APIName>")[0] + "</APIName>";
        String ApiDump =  ApiName + "<APIRoot>" + ApiDetails.split("<APIRoot>")[1].split("</APIPath>")[0]+"</APIPath>";
        
        //System.out.print(ApiDump);
        
        String AuditEntity = "<Audit>" + ReqId + ApiDetails + AuditVars + "</Audit>";

        //Map Audit Entity
        ApiAuditEntity ApiAuditEntity = xmlMapper.readValue(AuditEntity.getBytes(), ApiAuditEntity.class);
        if (ArtemisConsumer.apiAuditEntityLists.peek() != null){
        ArtemisConsumer.apiAuditEntityLists.peek().add(ApiAuditEntity);
        }

        //Map Dump Records
        String [] DumpRecords = msg.split("<DumpRecords>")[1].split("</DumpRecords>")[0].split("<Msg>");
        
        for (int i = 1; i < DumpRecords.length; i++) {
        	String DumpRecord = "<Dump>" + ReqId + ApiDump +DumpRecords[i].split("</Msg>")[0] + "</Dump>";
//        	System.out.print(DumpRecord);
            ApiDumpEntity ApiDumpEntity = xmlMapper.readValue(DumpRecord.getBytes(), ApiDumpEntity.class);
            if(ArtemisConsumer.apiDumpEntityLists.peek() != null){
                ArtemisConsumer.apiDumpEntityLists.peek().add(ApiDumpEntity);
            }
        }

        if (ArtemisConsumer.apiAuditEntityLists.peek() != null &&
                ArtemisConsumer.apiAuditEntityLists.peek().size() == batch_size) {
            ArtemisConsumer.t2 = new Timestamp(new Date().getTime());
            System.out.println("start reading "+ t1);
            System.out.println("end reading " +  t2);
            System.out.println("Read " + batch_size + " messages in " + (t2.getTime() - t1.getTime())/1000.0 + " s");
        	RunnableObject taskThread=new RunnableObject(insert,
                     apiDumpRepository,
                     apiAuditRepository,
                     ArtemisConsumer.apiDumpEntityLists.poll(),
                     ArtemisConsumer.apiAuditEntityLists.poll()
            );
            executor.execute(taskThread);
            ArtemisConsumer.isInserted = true;
            ArtemisConsumer.t1 = new Timestamp(new Date().getTime());

            // if there are no available lists, write to a file
            if(ArtemisConsumer.apiAuditEntityLists.peek() == null){
                // write to a file
            }
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

    public static void setFreeLists(List<ApiAuditEntity> apiAuditEntityList, List<ApiDumpEntity> apiDumpEntityList) {
        apiAuditEntityList.clear();
        apiAuditEntityLists.add(apiAuditEntityList);
        apiDumpEntityList.clear();
        apiDumpEntityLists.add(apiDumpEntityList);
    }
}
