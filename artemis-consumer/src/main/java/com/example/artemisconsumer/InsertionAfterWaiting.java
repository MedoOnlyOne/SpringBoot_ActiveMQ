package com.example.artemisconsumer;

import com.example.artemisconsumer.models.ApiAuditEntity;
import com.example.artemisconsumer.models.ApiDumpEntity;
import com.example.artemisconsumer.repositpries.ApiAuditRepository;
import com.example.artemisconsumer.repositpries.ApiDumpRepository;
import com.example.artemisconsumer.services.Insert;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

            if (passedTime >= MAX_LIST_WAITING_TIME && !ArtemisConsumer.isInsert()){
                this.apiAuditEntityList = ArtemisConsumer.getActiveApiAuditList();
                this.apiDumpEntityList = ArtemisConsumer.getActiveApiDumpList();

                if (this.apiAuditEntityList != null && this.apiDumpEntityList != null){
                    System.out.println("Inserting " + this.apiAuditEntityList.size() + " after waiting ................");
                    ArtemisConsumer.setInsert(true);
                    insert.insert(apiDumpRepository, apiAuditRepository, apiDumpEntityList, apiAuditEntityList);
                    ArtemisConsumer.setFreeLists(apiAuditEntityList, apiDumpEntityList);
                }
            }

            if (passedTime >= MAX_FILE_WAITING_TIME && ArtemisConsumer.isListsAvailable() && ArtemisConsumer.getIsWriteToFiles()){
                this.apiAuditEntityList = ArtemisConsumer.getApiAuditEntityLists().poll();
                this.apiDumpEntityList = ArtemisConsumer.getApiDumpEntityLists().poll();
                ArtemisConsumer.setIsWriteToFiles(false);
                try {
                    ArtemisConsumer.getAuditFileOutputStream().close();
                    ArtemisConsumer.getDumpFileOutputStream().close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                File folder = new File("./");
                File[] listOfFiles = folder.listFiles();
                List<File> textFiles = Arrays.stream(listOfFiles).filter(file -> file.isFile() && file.getName().endsWith(".txt"))
                        .collect(Collectors.toList());

                if (textFiles.size() > 0){
                    System.out.println("Inserting " + textFiles.size() + " files after waiting ................");
                    for(int i = 0; i < textFiles.size(); i++){
                        File currentFile = textFiles.get(i);
                        if (currentFile.getName().contains("Dump")){
                            List<ApiDumpEntity> dumpFileList = new ArrayList<>();
                            ArtemisConsumer.saveDumpFiles(currentFile.getName(), dumpFileList);
                            this.apiDumpEntityList.addAll(dumpFileList);
                        }
                        if (currentFile.getName().contains("Audit")){
                            List<ApiAuditEntity> auditFileList = new ArrayList<>();
                            ArtemisConsumer.saveAuditFiles(currentFile.getName(), auditFileList);
                            this.apiAuditEntityList.addAll(auditFileList);
                        }
                        currentFile.delete();
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
