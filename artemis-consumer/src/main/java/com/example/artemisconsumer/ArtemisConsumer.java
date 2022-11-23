package com.example.artemisconsumer;

import com.example.artemisconsumer.models.*;
import com.example.artemisconsumer.repositpries.ApiAuditRepository;
import com.example.artemisconsumer.repositpries.ApiDumpRepository;
import com.example.artemisconsumer.services.Insert;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    private static List<ApiAuditEntity> apiAuditEntityList = new ArrayList<>();
    private static List<ApiDumpEntity> apiDumpEntityList = new ArrayList<>();
    
    private final int batch_size = 500;
    private final int MAX_THREADS = 3;
    private ExecutorService executor = Executors.newFixedThreadPool(MAX_THREADS);
    private static Timestamp t1, t2;
    private static boolean IsInserted;

    static {
        ArtemisConsumer.t1 = new Timestamp(new Date().getTime());
        ArtemisConsumer.IsInserted = true;
    }
    @JmsListener(destination = "${jms.queue.destination}")
    public void receive(String msg) throws IOException, JAXBException, SAXException, ParserConfigurationException, TransformerException {
        if(ArtemisConsumer.apiAuditEntityList.size() == 0){
            ArtemisConsumer.t1 = new Timestamp(new Date().getTime());
            ArtemisConsumer.IsInserted = false;
        }

//        ObjectMapper mapper = new ObjectMapper();
//        APILogEntry dumpAuditMsg = mapper.readValue(msg, APILogEntry.class);

//        System.out.println("################################### Got: " + msg + " ###############################################################");


        XmlMapper xmlMapper = new XmlMapper();
        APILogEntry dumpAuditMsg = xmlMapper.readValue(msg.getBytes(), APILogEntry.class);

//        System.out.println("The message object" + dumpAuditMsg.toString());
        ApiAuditMsg auditMsg = new ApiAuditMsg(dumpAuditMsg.getReqID(), dumpAuditMsg.getAuditRecord(), dumpAuditMsg.getJson(), dumpAuditMsg.getText());
        ApiDumpMsg dumpMsg = new ApiDumpMsg(dumpAuditMsg.getReqID(), dumpAuditMsg.getDumpRecords(), dumpAuditMsg.getJson(), dumpAuditMsg.getText());

        // Dump
        for (Msgs recordMsg: dumpMsg.getDumpRecords().getMsgs()){
            ApiDumpEntity apiDumpEntity = new ApiDumpEntity(
                    null,
                    dumpMsg.getReqID(),
                    null,
                    null,
                    null,
                    null,
                    recordMsg.getMsgType(),
                    recordMsg.getPayload(),
                    recordMsg.getHeaderParams(),
                    recordMsg.getQueryParams(),
                    recordMsg.getPathParams(),
                    recordMsg.getTmstmp(),
                    recordMsg.getProviderName()
            );
            ArtemisConsumer.apiDumpEntityList.add(apiDumpEntity);
        }

        // Audit
        ApiAuditEntity apiAuditEntity = new ApiAuditEntity(
                null,
                auditMsg.getReqID(),
                auditMsg.getAuditRecord().getAPIDetails().getAPIName(),
                auditMsg.getAuditRecord().getAPIDetails().getAPIVersion(),
                auditMsg.getAuditRecord().getAPIDetails().getAPIType(),
                auditMsg.getAuditRecord().getAPIDetails().getHTTPMethod(),
                auditMsg.getAuditRecord().getAPIDetails().getProtocol(),
                auditMsg.getAuditRecord().getAPIDetails().getAPIRoot(),
                auditMsg.getAuditRecord().getAPIDetails().getOperationID(),
                auditMsg.getAuditRecord().getAPIDetails().getAPIPath(),
                auditMsg.getAuditRecord().getAPIDetails().getCatalogID(),
                auditMsg.getAuditRecord().getAPIDetails().getCatalogName(),
                auditMsg.getAuditRecord().getAPIDetails().getClientOrgID(),
                auditMsg.getAuditRecord().getAPIDetails().getClientOrgName(),
                auditMsg.getAuditRecord().getAPIDetails().getClientAppID(),
                auditMsg.getAuditRecord().getAPIDetails().getClientAppName(),
                auditMsg.getAuditRecord().getAuditVars().getTmstmp1(),
                auditMsg.getAuditRecord().getAuditVars().getTmstmp2(),
                auditMsg.getAuditRecord().getAuditVars().getTmstmp3(),
                auditMsg.getAuditRecord().getAuditVars().getTmstmp4(),
                auditMsg.getAuditRecord().getAuditVars().getTmstmpX().toString(),
                auditMsg.getAuditRecord().getAuditVars().getRejectionReason(),
                auditMsg.getAuditRecord().getAuditVars().getHTTPStatusCode(),
                auditMsg.getAuditRecord().getAuditVars().getUsrDef1(),
                auditMsg.getAuditRecord().getAuditVars().getUsrDef2(),
                auditMsg.getAuditRecord().getAuditVars().getUsrDef3(),
                auditMsg.getAuditRecord().getAuditVars().getUsrDef4(),
                auditMsg.getAuditRecord().getAuditVars().getUsrDef5(),
                auditMsg.getAuditRecord().getAuditVars().getUsrDef6(),
                auditMsg.getAuditRecord().getAuditVars().getUsrDef7(),
                auditMsg.getAuditRecord().getAuditVars().getUsrDef8(),
                auditMsg.getAuditRecord().getAuditVars().getUsrDef9(),
                auditMsg.getAuditRecord().getAuditVars().getUsrDef10(),
                auditMsg.getAuditRecord().getAuditVars().getUsrDef11(),
                auditMsg.getAuditRecord().getAuditVars().getUsrDef12(),
                auditMsg.getAuditRecord().getAuditVars().getUsrDef13(),
                auditMsg.getAuditRecord().getAuditVars().getUsrDef14(),
                auditMsg.getAuditRecord().getAuditVars().getUsrDef15()
        );
        ArtemisConsumer.apiAuditEntityList.add(apiAuditEntity);
  
        if (ArtemisConsumer.apiAuditEntityList.size() == batch_size) {
            ArtemisConsumer.t2 = new Timestamp(new Date().getTime());
            System.out.println("start reading "+ t1);
            System.out.println("end reading " +  t2);
            System.out.println("Read " + batch_size + " messages in " + (t2.getTime() - t1.getTime())/1000.0 + " s");

        	RunnableObject taskThread=new RunnableObject(insert,
                     apiDumpRepository,
                     apiAuditRepository,
                     ArtemisConsumer.apiDumpEntityList ,
                     ArtemisConsumer.apiAuditEntityList
             		  );
            executor.execute(taskThread);
            ArtemisConsumer.apiAuditEntityList.clear();
            ArtemisConsumer.apiDumpEntityList.clear();
            ArtemisConsumer.IsInserted = true;
            ArtemisConsumer.t1 = new Timestamp(new Date().getTime());
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

    public static void setT1(Timestamp t1) {
        ArtemisConsumer.t1 = t1;
    }

    public static Timestamp getT2() {
        return ArtemisConsumer.t2;
    }

    public static void setT2(Timestamp t2) {
        ArtemisConsumer.t2 = t2;
    }

    public static List<ApiAuditEntity> getApiAuditEntityList() {
        return ArtemisConsumer.apiAuditEntityList;
    }

    public static void setApiAuditEntityList(List<ApiAuditEntity> apiAuditEntityList) {
        ArtemisConsumer.apiAuditEntityList = apiAuditEntityList;
    }

    public static List<ApiDumpEntity> getApiDumpEntityList() {
        return ArtemisConsumer.apiDumpEntityList;
    }

    public static void setApiDumpEntityList(List<ApiDumpEntity> apiDumpEntityList) {
        ArtemisConsumer.apiDumpEntityList = apiDumpEntityList;
    }
}
