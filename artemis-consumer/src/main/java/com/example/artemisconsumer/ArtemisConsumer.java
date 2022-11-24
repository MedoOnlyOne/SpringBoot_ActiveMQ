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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
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

    private static List<List<ApiAuditEntity>> apiAuditEntityLists = new ArrayList<>(10);
    private static List<List<ApiDumpEntity>> apiDumpEntityLists = new ArrayList<>(10);
    private static List<Boolean> freeLists = new ArrayList<>(10);
    private static int activeList;

    private final int batch_size = 500;
    private final int MAX_THREADS = 3;
    private ExecutorService executor = Executors.newFixedThreadPool(MAX_THREADS);
    private static Timestamp t1, t2;
    private static boolean IsInserted;

    static {
        ArtemisConsumer.t1 = new Timestamp(new Date().getTime());
        ArtemisConsumer.IsInserted = true;
        for (int i = 0; i < 10; i++){
            ArtemisConsumer.freeLists.add(i, true);
            ArtemisConsumer.apiDumpEntityLists.add(i, new ArrayList<ApiDumpEntity>());
            ArtemisConsumer.apiAuditEntityLists.add(i, new ArrayList<ApiAuditEntity>());
        }
        ArtemisConsumer.freeLists.set(0, false);
        ArtemisConsumer.activeList = 0;
    }
    @JmsListener(destination = "${jms.queue.destination}")
    public void receiveEiarDump(String msg) throws IOException, JAXBException, SAXException, ParserConfigurationException, TransformerException {
        if(ArtemisConsumer.apiAuditEntityLists.get(activeList).size() == 0){
            ArtemisConsumer.t1 = new Timestamp(new Date().getTime());
            ArtemisConsumer.IsInserted = false;
        }

//        System.out.print("XML MESSAGE"+msg);
        
        XmlMapper xmlMapper = new XmlMapper();
        
        String ReqId = "<ReqID>"+msg.split("<ReqID>")[1].split("</ReqID>")[0] + "</ReqID>";
        String ApiDetails =msg.split("<AuditRecord>")[1].split("</AuditRecord>")[0].split("<APIDetails>")[1].split("</APIDetails>")[0];
        String AuditVars = msg.split("<AuditRecord>")[1].split("</AuditRecord>")[0].split("<AuditVars>")[1].split("</AuditVars>")[0];
        
        String AuditEntity = "<Audit>" + ReqId + ApiDetails + AuditVars + "</Audit>";
      //Map Audit Entity
        ApiAuditEntity ApiAuditEntity = xmlMapper.readValue(AuditEntity.getBytes(), ApiAuditEntity.class);
        ArtemisConsumer.apiAuditEntityLists.get(activeList).add(ApiAuditEntity);
               
       //Map Dump Records 
        String [] DumpRecords = msg.split("<DumpRecords>")[1].split("</DumpRecords>")[0].split("<Msg>");
        
        for (int i = 1; i < DumpRecords.length; i++) {
        	String DumpRecord = "<Dump>" + ReqId + DumpRecords[i].split("</Msg>")[0] + "</Dump>";
            ApiDumpEntity ApiDumpEntity =  xmlMapper.readValue(DumpRecord.getBytes(), ApiDumpEntity.class);
          ArtemisConsumer.apiDumpEntityLists.get(activeList).add(ApiDumpEntity);
        }
        
        //APILogEntry dumpAuditMsg = xmlMapper.readValue(msg.getBytes(), APILogEntry.class);
        
        
//        System.out.println("AuditEntityATTRIBUTE"+ApiAuditEntity.getaApiName());
        
//        ApiDumpEntity ApiDumpEntity1 =  xmlMapper.readValue(DumpRecord1.getBytes(), ApiDumpEntity.class);
////        System.out.println("DumpEntityATTRIBUTE"+ApiAuditEntity1.getMdMsgTp());
//        ArtemisConsumer.apiDumpEntityLists.get(activeList).add(ApiDumpEntity1);
//        
//        ApiDumpEntity ApiDumpEntity2 =  xmlMapper.readValue(DumpRecord2.getBytes(), ApiDumpEntity.class);     
//        ArtemisConsumer.apiDumpEntityLists.get(activeList).add(ApiDumpEntity2);
//        
//        ApiDumpEntity ApiDumpEntity3 =  xmlMapper.readValue(DumpRecord3.getBytes(), ApiDumpEntity.class);     
//        ArtemisConsumer.apiDumpEntityLists.get(activeList).add(ApiDumpEntity3);
        
//        ObjectMapper mapper = new ObjectMapper();
//        APILogEntry dumpAuditMsg = mapper.readValue(msg , APILogEntry.class );
        //ApiAuditEntity apiAuditEntitytrial2 = mapper.
        //msg.split("");  
        //System.out.println("Api details"+dumpAuditMsg.getAuditRecord().getAPIDetails());
//               
        //ApiAuditMsg auditMsg = new ApiAuditMsg(dumpAuditMsg.getReqID(), dumpAuditMsg.getAuditRecord(), dumpAuditMsg.getJson(), dumpAuditMsg.getText());
        //ApiDumpMsg dumpMsg = new ApiDumpMsg(dumpAuditMsg.getReqID(), dumpAuditMsg.getDumpRecords(), dumpAuditMsg.getJson(), dumpAuditMsg.getText());      

        // Dump
//        for (Msgs recordMsg: dumpAuditMsg.getDumpRecords().getMsgs()){
//            ApiDumpEntity apiDumpEntity = new ApiDumpEntity(
//                    null,
//                    dumpAuditMsg.getReqID(),
//                    null,
//                    null,
//                    null,
//                    null,
//                    recordMsg.getMsgType(),
//                    recordMsg.getPayload(),
//                    recordMsg.getHeaderParams(),
//                    recordMsg.getQueryParams(),
//                    recordMsg.getPathParams(),
//                    recordMsg.getTmstmp(),
//                    recordMsg.getProviderName()
//            );
//            ArtemisConsumer.apiDumpEntityLists.get(activeList).add(apiDumpEntity);
//        }

        // Audit
//        ApiAuditEntity apiAuditEntity = new ApiAuditEntity(
//                null,
//                auditMsg.getReqID(),
//                auditMsg.getAuditRecord().getAPIDetails().getAPIName(),
//                auditMsg.getAuditRecord().getAPIDetails().getAPIVersion(),
//                auditMsg.getAuditRecord().getAPIDetails().getAPIType(),
//                auditMsg.getAuditRecord().getAPIDetails().getHTTPMethod(),
//                auditMsg.getAuditRecord().getAPIDetails().getProtocol(),
//                auditMsg.getAuditRecord().getAPIDetails().getAPIRoot(),
//                auditMsg.getAuditRecord().getAPIDetails().getOperationID(),
//                auditMsg.getAuditRecord().getAPIDetails().getAPIPath(),
//                auditMsg.getAuditRecord().getAPIDetails().getCatalogID(),
//                auditMsg.getAuditRecord().getAPIDetails().getCatalogName(),
//                auditMsg.getAuditRecord().getAPIDetails().getClientOrgID(),
//                auditMsg.getAuditRecord().getAPIDetails().getClientOrgName(),
//                auditMsg.getAuditRecord().getAPIDetails().getClientAppID(),
//                auditMsg.getAuditRecord().getAPIDetails().getClientAppName(),
//                auditMsg.getAuditRecord().getAuditVars().getTmstmp1(),
//                auditMsg.getAuditRecord().getAuditVars().getTmstmp2(),
//                auditMsg.getAuditRecord().getAuditVars().getTmstmp3(),
//                auditMsg.getAuditRecord().getAuditVars().getTmstmp4(),
//                auditMsg.getAuditRecord().getAuditVars().getTmstmpX().toString(),
//                auditMsg.getAuditRecord().getAuditVars().getRejectionReason(),
//                auditMsg.getAuditRecord().getAuditVars().getHTTPStatusCode(),
//                auditMsg.getAuditRecord().getAuditVars().getUsrDef1(),
//                auditMsg.getAuditRecord().getAuditVars().getUsrDef2(),
//                auditMsg.getAuditRecord().getAuditVars().getUsrDef3(),
//                auditMsg.getAuditRecord().getAuditVars().getUsrDef4(),
//                auditMsg.getAuditRecord().getAuditVars().getUsrDef5(),
//                auditMsg.getAuditRecord().getAuditVars().getUsrDef6(),
//                auditMsg.getAuditRecord().getAuditVars().getUsrDef7(),
//                auditMsg.getAuditRecord().getAuditVars().getUsrDef8(),
//                auditMsg.getAuditRecord().getAuditVars().getUsrDef9(),
//                auditMsg.getAuditRecord().getAuditVars().getUsrDef10(),
//                auditMsg.getAuditRecord().getAuditVars().getUsrDef11(),
//                auditMsg.getAuditRecord().getAuditVars().getUsrDef12(),
//                auditMsg.getAuditRecord().getAuditVars().getUsrDef13(),
//                auditMsg.getAuditRecord().getAuditVars().getUsrDef14(),
//                auditMsg.getAuditRecord().getAuditVars().getUsrDef15()
//        );
       
  
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
//            ArtemisConsumer.apiAuditEntityList.clear();
//            ArtemisConsumer.apiDumpEntityList.clear();
            ArtemisConsumer.IsInserted = true;
            ArtemisConsumer.t1 = new Timestamp(new Date().getTime());

            boolean foundList = false;
            // Get next list to work with
            for (int i = 0; i < 10; i++){
                if (ArtemisConsumer.freeLists.get(i)) {
                    foundList = true;
                    ArtemisConsumer.activeList = i;
                    ArtemisConsumer.freeLists.set(i, false);
                }
            }
            if(!foundList){
                ArtemisConsumer.activeList = freeLists.size();
                ArtemisConsumer.freeLists.add(ArtemisConsumer.activeList, true);
                ArtemisConsumer.apiDumpEntityLists.add(ArtemisConsumer.activeList, new ArrayList<ApiDumpEntity>());
                ArtemisConsumer.apiAuditEntityLists.add(ArtemisConsumer.activeList, new ArrayList<ApiAuditEntity>());
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

    public static void setFreeLists(int active) {
        ArtemisConsumer.freeLists.set(active, true);
    }

    public static int getActiveList() {
        return activeList;
    }
}
