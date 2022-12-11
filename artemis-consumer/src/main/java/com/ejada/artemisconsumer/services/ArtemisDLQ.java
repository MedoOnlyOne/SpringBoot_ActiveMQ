package com.ejada.artemisconsumer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class ArtemisDLQ {
    @Autowired JmsTemplate jmsTemplate;

    @Value("${jms.queue.dlq}")
    String dlqDestination;

    @Value("${jms.queue.destination}")
    String mainQueueDestination;

    public void sendDLQ(String msg) {
        jmsTemplate.convertAndSend(dlqDestination, msg);
    }

    public void sendMainQ(String msg) {
        jmsTemplate.convertAndSend(mainQueueDestination, msg);
    }
}

