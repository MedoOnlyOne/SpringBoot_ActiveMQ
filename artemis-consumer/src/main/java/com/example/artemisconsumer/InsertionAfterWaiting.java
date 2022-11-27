package com.example.artemisconsumer;

import com.example.artemisconsumer.models.ApiAuditEntity;
import com.example.artemisconsumer.models.ApiDumpEntity;
import com.example.artemisconsumer.repositpries.ApiAuditRepository;
import com.example.artemisconsumer.repositpries.ApiDumpRepository;
import com.example.artemisconsumer.services.Insert;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class InsertionAfterWaiting implements Runnable{
    private final long MAX_WAITING_TIME = 20;

    private Insert insert;
    private ApiAuditRepository apiAuditRepository;
    private ApiDumpRepository apiDumpRepository;

    public InsertionAfterWaiting(Insert insert, ApiAuditRepository apiAuditRepository, ApiDumpRepository apiDumpRepository) {
        this.insert = insert;
        this.apiAuditRepository = apiAuditRepository;
        this.apiDumpRepository = apiDumpRepository;
    }

    private List<ApiDumpEntity> apiDumpEntityList;
    private List<ApiAuditEntity> apiAuditEntityList;

    @Override
    public void run() {
        while(true) {
            Timestamp now = new Timestamp(new Date().getTime());
            if(!ArtemisConsumer.isInsert() &&
                    (now.getTime() - ArtemisConsumer.getT1().getTime()/1000) >= MAX_WAITING_TIME){
                if(ArtemisConsumer.getApiAuditEntityLists().peek() != null &&
                        ArtemisConsumer.getApiDumpEntityLists().peek() != null){
                    this.apiAuditEntityList = ArtemisConsumer.getApiAuditEntityLists().poll();
                    this.apiDumpEntityList = ArtemisConsumer.getApiDumpEntityLists().poll();
                }

                System.out.println("Inserting " + this.apiAuditEntityList.size() + " after waiting");

                ArtemisConsumer.setInsert(true);
                System.out.println("The actual flag is " + ArtemisConsumer.isInsert());
                insert.insert(apiDumpRepository, apiAuditRepository, apiDumpEntityList, apiAuditEntityList);
                ArtemisConsumer.setFreeLists(apiAuditEntityList, apiDumpEntityList);
            }
            try {
                Thread.sleep(MAX_WAITING_TIME * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
