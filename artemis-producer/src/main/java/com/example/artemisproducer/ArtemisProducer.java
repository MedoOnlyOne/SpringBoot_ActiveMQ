package com.example.artemisproducer;

import com.example.artemisproducer.models.ApiAuditMsg;
import com.example.artemisproducer.models.ApiDumpMsg;
import com.example.artemisproducer.models.DumpMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class ArtemisProducer {
    @Autowired JmsTemplate jmsTemplate;

    @Value("${jms.queue.destination}")
    String destinationQueue;

    @Value("${jms.queue.destination.dump}")
    String dumpDestinationQueue;

    @Value("${jms.queue.destination.audit}")
    String auditDestinationQueue;

    public void sendEairDump(DumpMsg msg) {
        jmsTemplate.convertAndSend(destinationQueue, msg);
    }

    public void sendApiDump(ApiDumpMsg msg) {
        jmsTemplate.convertAndSend(dumpDestinationQueue, msg);
    }

    public void sendApiAudit(ApiAuditMsg msg) {
        jmsTemplate.convertAndSend(auditDestinationQueue, msg);
    }
}
