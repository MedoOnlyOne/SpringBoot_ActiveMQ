package com.example.artemisconsumer;

import com.example.artemisconsumer.models.*;
import com.example.artemisconsumer.repositpries.ApiAuditEntityRepository;
import com.example.artemisconsumer.repositpries.ApiDumpEntityRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class ArtemisConsumer  {
    @Autowired
    ApiAuditEntityRepository apiAuditEntityRepository;

    @Autowired
    ApiDumpEntityRepository apiDumpEntityRepository;

    private final int MAX_THREADS = 5;
    private ExecutorService executor = Executors.newFixedThreadPool(MAX_THREADS);

    @JmsListener(destination = "${jms.queue.destination}")
    public void receive(String msg) throws JsonProcessingException {
        // Map string message to APILogEntry Object
        Timestamp t1 = new Timestamp(new Date().getTime());
        ObjectMapper mapper = new ObjectMapper();
        APILogEntry dumpAuditMsg = mapper.readValue(msg, APILogEntry.class);
        System.out.println("Got Message: " + dumpAuditMsg.toString());

        // Separate Audit, and Dump messages
        ApiAuditMsg auditMsg = new ApiAuditMsg(dumpAuditMsg.getReqID(), dumpAuditMsg.getAuditRecord(), dumpAuditMsg.getJson(), dumpAuditMsg.getText());
        ApiDumpMsg dumpMsg = new ApiDumpMsg(dumpAuditMsg.getReqID(), dumpAuditMsg.getDumpRecords(), dumpAuditMsg.getJson(), dumpAuditMsg.getText());

        // Generate Dump Entries
        List<ApiDumpEntity> apiDumpEntityList = new ArrayList<>();
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

            apiDumpEntityList.add(apiDumpEntity);
        }

        // Generate Audit Entry
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

        Timestamp t2 = new Timestamp(new Date().getTime());

        System.out.println("Before calling thread" +new Timestamp(new Date().getTime()));
        RunnableObject taskThread=new RunnableObject(apiAuditEntityRepository,apiAuditEntity ,
        		apiDumpEntityRepository , apiDumpEntityList);
		executor.submit(taskThread);
		System.out.println("After calling thread" +new Timestamp(new Date().getTime()));

        System.out.println("start reading "+ t1);
        System.out.println("end reading" +  t2);
    }
    
}
