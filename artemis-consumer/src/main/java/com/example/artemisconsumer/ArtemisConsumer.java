package com.example.artemisconsumer;

import com.example.artemisconsumer.models.*;
import com.example.artemisconsumer.repositpries.ApiAuditEntityRepository;
import com.example.artemisconsumer.repositpries.ApiAuditRepository;
import com.example.artemisconsumer.repositpries.ApiDumpEntityRepository;

import com.example.artemisconsumer.repositpries.ApiDumpRepository;
import com.example.artemisconsumer.services.Insert;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

@Component
public class ArtemisConsumer  {
    @Autowired
    private ApiDumpRepository apiDumpRepository;
    @Autowired
    private ApiAuditRepository apiAuditRepository;

    @Autowired
    private Insert insert;

    private static final int MAX_LISTS = 10;
    private static List<List<ApiAuditEntity>> apiAuditEntityLists = new ArrayList<>(MAX_LISTS);
    private static List<List<ApiDumpEntity>> apiDumpEntityLists = new ArrayList<>(MAX_LISTS);
    private static Queue<Integer> freeLists = new LinkedList<>();
    private static int activeList;

    private final int batch_size = 500;
    private final int MAX_THREADS = 3;
    private ExecutorService executor = Executors.newFixedThreadPool(MAX_THREADS);
    private static Timestamp t1, t2;
    private static boolean IsInserted;

    static {
        ArtemisConsumer.t1 = new Timestamp(new Date().getTime());
        ArtemisConsumer.IsInserted = true;
        for (int i = 0; i < MAX_LISTS; i++){
            ArtemisConsumer.freeLists.add(i);
            ArtemisConsumer.apiDumpEntityLists.add(i, new ArrayList<ApiDumpEntity>());
            ArtemisConsumer.apiAuditEntityLists.add(i, new ArrayList<ApiAuditEntity>());
        }
        ArtemisConsumer.activeList = ArtemisConsumer.freeLists.poll();
    }
    @JmsListener(destination = "${jms.queue.destination}")
    public void receive(String msg) throws IOException, JAXBException, SAXException, ParserConfigurationException, TransformerException {
        if(ArtemisConsumer.apiAuditEntityLists.get(activeList).size() == 0){
            ArtemisConsumer.t1 = new Timestamp(new Date().getTime());
            ArtemisConsumer.IsInserted = false;
        }

//        System.out.print("XML MESSAGE"+msg);
        
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
        ArtemisConsumer.apiAuditEntityLists.get(activeList).add(ApiAuditEntity);
               
        //Map Dump Records
        String [] DumpRecords = msg.split("<DumpRecords>")[1].split("</DumpRecords>")[0].split("<Msg>");
        
        for (int i = 1; i < DumpRecords.length; i++) {
        	String DumpRecord = "<Dump>" + ReqId + ApiDump +DumpRecords[i].split("</Msg>")[0] + "</Dump>";
//        	System.out.print(DumpRecord);
            ApiDumpEntity ApiDumpEntity =  xmlMapper.readValue(DumpRecord.getBytes(), ApiDumpEntity.class);
          ArtemisConsumer.apiDumpEntityLists.get(activeList).add(ApiDumpEntity);
        }

        if (ArtemisConsumer.apiAuditEntityLists.get(activeList).size() == batch_size) {
            ArtemisConsumer.t2 = new Timestamp(new Date().getTime());
            System.out.println("start reading "+ t1);
            System.out.println("end reading " +  t2);
            System.out.println("Read " + batch_size + " messages in " + (t2.getTime() - t1.getTime())/1000.0 + " s");
            
        	RunnableObject taskThread=new RunnableObject(insert,
                     apiDumpRepository,
                     apiAuditRepository,
                     ArtemisConsumer.apiDumpEntityLists.get(activeList) ,
                     ArtemisConsumer.apiAuditEntityLists.get(activeList),
                    ArtemisConsumer.activeList
             		  );
            executor.execute(taskThread);
            ArtemisConsumer.IsInserted = true;
            ArtemisConsumer.t1 = new Timestamp(new Date().getTime());

            // Get next list to work with
            if(ArtemisConsumer.freeLists.peek() != null){
                ArtemisConsumer.activeList = ArtemisConsumer.freeLists.poll();
            } else {
                // Write to a file
            }
        }
    }

    public static boolean isInsert() {
        return ArtemisConsumer.IsInserted;
    }

    public static void setInsert(boolean insert) {
        ArtemisConsumer.IsInserted = insert;
    }

    public static Timestamp getT1() {
        return ArtemisConsumer.t1;
    }

    public static List<ApiAuditEntity> getApiAuditEntityList() {
        return ArtemisConsumer.apiAuditEntityLists.get(activeList);
    }

    public static List<ApiDumpEntity> getApiDumpEntityList() {
        return ArtemisConsumer.apiDumpEntityLists.get(activeList);
    }

    public static int getActiveList() {
        return activeList;
    }

    public static void setFreeLists(int freeList) {
        ArtemisConsumer.freeLists.add(freeList);
        ArtemisConsumer.apiAuditEntityLists.get(freeList).clear();
        ArtemisConsumer.apiDumpEntityLists.get(freeList).clear();
    }
}
