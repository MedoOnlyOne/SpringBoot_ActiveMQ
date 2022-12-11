package com.ejada.artemisconsumer.services;

import com.ejada.artemisconsumer.models.ApiAuditEntity;
import com.ejada.artemisconsumer.models.ApiDumpEntity;
import com.ejada.artemisconsumer.repositpries.ApiAuditRepository;
import com.ejada.artemisconsumer.repositpries.ApiDumpRepository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class InsertionAfterWaiting implements Runnable{
    private final long MAX_LIST_WAITING_TIME = 90;
    private final long MAX_FILE_WAITING_TIME = 300;

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
    private List<String > messageList;

    @Override
    public void run() {
        while(true) {
            Timestamp now = new Timestamp(new Date().getTime());
            double passedTime = now.getTime() - ArtemisConsumer.getT1().getTime()/1000;

            if (passedTime >= MAX_LIST_WAITING_TIME && !ArtemisConsumer.isInsert()){
                this.apiAuditEntityList = ArtemisConsumer.getActiveApiAuditList();
                this.apiDumpEntityList = ArtemisConsumer.getActiveApiDumpList();
                this.messageList = ArtemisConsumer.getActiveMessageList();

                if (this.apiAuditEntityList != null && this.apiDumpEntityList != null){
                    System.out.println("Inserting " + this.apiAuditEntityList.size() + " after waiting ................");
                    ArtemisConsumer.setInsert(true);
                    insert.insert(apiDumpEntityList, apiAuditEntityList, messageList);
                    ArtemisConsumer.setFreeLists(apiAuditEntityList, apiDumpEntityList, messageList);
                }
            }
            try {
                Thread.sleep(MAX_LIST_WAITING_TIME * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
