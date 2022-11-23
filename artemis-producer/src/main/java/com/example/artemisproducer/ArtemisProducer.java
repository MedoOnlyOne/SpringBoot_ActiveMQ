package com.example.artemisproducer;

import com.example.artemisproducer.models.APILogEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class ArtemisProducer {
    @Autowired JmsTemplate jmsTemplate;

    @Value("${jms.queue.destination}")
    String destinationQueue;

    public void sendJsonString(APILogEntry msg) {
        jmsTemplate.convertAndSend(destinationQueue, msg);
    }

    public void sendXMLStrimg(String msg) {
        jmsTemplate.convertAndSend(destinationQueue, msg);
    }
}
