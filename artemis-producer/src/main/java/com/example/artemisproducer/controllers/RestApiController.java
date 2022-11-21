package com.example.artemisproducer.controllers;

import com.example.artemisproducer.ArtemisProducer;
import com.example.artemisproducer.models.APILogEntry;
import com.example.artemisproducer.models.ApiAuditMsg;
import com.example.artemisproducer.models.ApiDumpMsg;
import com.example.artemisproducer.models.DumpMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/send")
public class RestApiController {
    @Autowired
    ArtemisProducer producer;
    @PostMapping(path ="/eai", consumes = {MediaType.APPLICATION_XML_VALUE})
    public String produceEai(@RequestBody DumpMsg msg) {
        System.out.println(msg.toString());
        System.out.println(msg.getMsgRqHdr().getMsg().getRqID());
        producer.sendEairDump(msg);
        return "Done";
    }

    @PostMapping(path = "/api", consumes = {MediaType.APPLICATION_XML_VALUE})
    public String produce(@RequestBody APILogEntry msg) {
        System.out.println(msg.toString());
        System.out.println(msg.getDumpRecords().getMsgs().size());

        ApiAuditMsg auditMsg = new ApiAuditMsg(msg.getReqID(), msg.getAuditRecord(), msg.getJson(), msg.getText());
        ApiDumpMsg dumpMsg = new ApiDumpMsg(msg.getReqID(), msg.getDumpRecords(), msg.getJson(), msg.getText());

        producer.sendApiDump(dumpMsg);
        producer.sendApiAudit(auditMsg);
        return "Done";
    }
}