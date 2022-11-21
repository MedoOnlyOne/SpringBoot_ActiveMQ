package com.example.artemisconsumer;

import com.example.artemisconsumer.models.*;
import com.example.artemisconsumer.repositpries.ApiAuditEntityRepository;
import com.example.artemisconsumer.repositpries.ApiDumpEntityRepository;
import com.example.artemisconsumer.repositpries.EaiDumpRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ArtemisConsumer {
    @Autowired
    EaiDumpRepository eaiDumpRepository;

    @Autowired
    ApiAuditEntityRepository apiAuditEntityRepository;

    @Autowired
    ApiDumpEntityRepository apiDumpEntityRepository;

    @JmsListener(destination = "${jms.queue.destination}")
    public void receiveEiarDump(String msg) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        DumpMsg dumpMsg = mapper.readValue(msg, DumpMsg.class);
        System.out.println("Got Message: " + dumpMsg.toString());
        System.out.println(dumpMsg.getMsgRqHdr().getMsg().getRqID());

        EaiDumpEntity dump = new EaiDumpEntity();
        dump.setMdRequestId(dumpMsg.getMsgRqHdr().getMsg().getRqID());
        dump.setMdCreationTmstmp(dumpMsg.getTmstmp());
        dump.setMdSvcPrvdrId(dumpMsg.getSvcPrvdrID());
        dump.setMdFlowId(dumpMsg.getFlowId());
        dump.setMdMsgTp(dumpMsg.getDumpMsgType());
        dump.setMdMsgData(dumpMsg.getEAIMsg());
        System.out.println(dump.toString());

        eaiDumpRepository.save(dump);

    }

    @JmsListener(destination = "${jms.queue.destination.dump}")
    public void receiveApiDump(String msg) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ApiDumpMsg dumpMsg = mapper.readValue(msg, ApiDumpMsg.class);
        System.out.println("Got Dump Message: " + dumpMsg.toString());

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

        apiDumpEntityRepository.saveAll(apiDumpEntityList);
    }

    @JmsListener(destination = "${jms.queue.destination.audit}")
    public void receiveApiAudit(String msg) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ApiAuditMsg auditMsg = mapper.readValue(msg, ApiAuditMsg.class);
        System.out.println("Got Audit Message: " + auditMsg.toString());


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

        apiAuditEntityRepository.save(apiAuditEntity);
    }
}
