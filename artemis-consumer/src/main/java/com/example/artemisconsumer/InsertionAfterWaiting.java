package com.example.artemisconsumer;

import com.example.artemisconsumer.models.ApiAuditEntity;
import com.example.artemisconsumer.models.ApiDumpEntity;
import com.example.artemisconsumer.repositpries.ApiAuditRepository;
import com.example.artemisconsumer.repositpries.ApiDumpRepository;
import com.example.artemisconsumer.services.Insert;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
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

    @Override
    public void run() {
        while(true) {
            Timestamp now = new Timestamp(new Date().getTime());
            double passedTime = now.getTime() - ArtemisConsumer.getT1().getTime()/1000;

            if (passedTime >= MAX_LIST_WAITING_TIME &&
                    !ArtemisConsumer.isInsert() &&
                    ArtemisConsumer.isIsListsAvailable()){
                this.apiAuditEntityList = ArtemisConsumer.getApiAuditEntityLists().poll();
                this.apiDumpEntityList = ArtemisConsumer.getApiDumpEntityLists().poll();

                System.out.println("Inserting " + this.apiAuditEntityList.size() + " after waiting");

                ArtemisConsumer.setInsert(true);
                System.out.println("The actual flag is " + ArtemisConsumer.isInsert());
                insert.insert(apiDumpRepository, apiAuditRepository, apiDumpEntityList, apiAuditEntityList);
                ArtemisConsumer.setFreeLists(apiAuditEntityList, apiDumpEntityList);
            }

            if (passedTime >= MAX_FILE_WAITING_TIME &&
                    ArtemisConsumer.isIsListsAvailable()){
                this.apiAuditEntityList = ArtemisConsumer.getApiAuditEntityLists().poll();
                this.apiDumpEntityList = ArtemisConsumer.getApiDumpEntityLists().poll();

                File folder = new File("./");
                File[] listOfFiles = folder.listFiles();
                List<File> textFiles = new ArrayList<>();

                for (int i = 0; i < listOfFiles.length; i++) {
                    File file = listOfFiles[i];
                    if (file.isFile() && file.getName().endsWith(".txt")) {
                        textFiles.add(listOfFiles[i]);
                    }
                }
                if (textFiles.size() > 0){
                    for(int i = 0; i < textFiles.size(); i++){
                        if (textFiles.get(i).getName().contains("Dump") &&
                                !textFiles.get(i).getName().equals(ArtemisConsumer.getDumpFileUnderProcessing())
//                                && !textFiles.get(i).getName().equals(ArtemisConsumer.getDumpFileName())
                        ){
                            ArtemisConsumer.setDumpFileUnderProcessing(textFiles.get(i).getName());
                            this.apiDumpEntityList.addAll(ArtemisConsumer.saveDumpFiles(textFiles.get(i).getName(), this.apiDumpEntityList));
                            textFiles.get(i).delete();
                            ArtemisConsumer.setDumpFileUnderProcessing("");
                        }

                        if (textFiles.get(i).getName().contains("Audit") &&
                                !textFiles.get(i).getName().equals(ArtemisConsumer.getAuditFileUnderProcessing())
//                                && !textFiles.get(i).getName().equals(ArtemisConsumer.getAuditFileName())
                        ){
                            ArtemisConsumer.setAuditFileUnderProcessing(textFiles.get(i).getName());
                            this.apiAuditEntityList.addAll(ArtemisConsumer.saveAuditFiles(textFiles.get(i).getName(), this.apiAuditEntityList));
                            textFiles.get(i).delete();
                            ArtemisConsumer.setAuditFileUnderProcessing("");
                        }
                    }
                    insert.insert(apiDumpRepository, apiAuditRepository, this.apiDumpEntityList,  this.apiAuditEntityList);
                }
                ArtemisConsumer.setFreeLists(this.apiAuditEntityList, this.apiDumpEntityList);
            }
            try {
                Thread.sleep(MAX_LIST_WAITING_TIME * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
