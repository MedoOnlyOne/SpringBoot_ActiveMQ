package com.ejada.artemisconsumer.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;


@Component
public class DlqConsumer  {
    @Autowired
    ArtemisDLQ dlq;
    @Autowired
    JmsTemplate jmsTemplate;
    long DLQ_SLEEP_TIME = 60;
    static Timestamp now , t;

    static {
        t = new Timestamp(new Date().getTime());
    }
    @JmsListener(destination = "${jms.queue.dlq}")
    public void receive(String msg) throws InterruptedException {
        now = new Timestamp(new Date().getTime());
        if ((now.getTime() - t.getTime()) / 1000 > 5){
            Thread.sleep(DLQ_SLEEP_TIME * 1000);
        }
        dlq.sendMainQ(msg);
        t = new Timestamp(new Date().getTime());
    }
}
