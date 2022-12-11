package com.ejada.artemisconsumer.services;

import com.ejada.artemisconsumer.models.ApiAuditEntity;
import com.ejada.artemisconsumer.models.ApiDumpEntity;
import com.ejada.artemisconsumer.repositpries.AuditDumpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class Insert {
    @Autowired
    ArtemisDLQ dlqProducer;
    @Autowired
    AuditDumpRepository auditDumpRepository;

    private  List<ApiDumpEntity> apiDumpEntityList ;
    private List<ApiAuditEntity> apiAuditEntityList ;

    public void insert(List<ApiDumpEntity> apiDumpEntityList, List<ApiAuditEntity> apiAuditEntityList,
                       List<String> messageList){
        this.apiDumpEntityList = new ArrayList<>(apiDumpEntityList) ;
        this.apiAuditEntityList = new ArrayList<>(apiAuditEntityList);
        try{
            Timestamp t1 = new Timestamp(new Date().getTime());
            auditDumpRepository.saveAll(apiDumpEntityList, apiAuditEntityList);
            Timestamp t2 = new Timestamp(new Date().getTime());
            System.out.println("time before insertion "+t1);
            System.out.println("time after insertion "+t2);
            System.out.println("Batch inserting in  " + (t2.getTime() - t1.getTime())/1000.0 + " s");

        } catch (Exception e) {
            System.out.println("=============================> Exception <==========================");
            // push to DLQ
            messageList.forEach((msg ->{
                dlqProducer.sendDLQ(msg);
            }));
            ArtemisConsumer.isDLQ = true;
        }
    }
}
