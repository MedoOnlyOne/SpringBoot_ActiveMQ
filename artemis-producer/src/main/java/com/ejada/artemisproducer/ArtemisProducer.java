package com.ejada.artemisproducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class ArtemisProducer {
    @Autowired JmsTemplate jmsTemplate;

    @Value("${jms.queue.destination}")
    String destinationQueue;

    public void sendXMLStrimg(String msg) {
        jmsTemplate.convertAndSend(destinationQueue, msg);
    }
}
