package com.example.artemisconsumer.services;

import com.example.artemisconsumer.models.ApiAuditEntity;
import com.example.artemisconsumer.models.ApiDumpEntity;
import com.example.artemisconsumer.repositpries.ApiAuditRepository;
import com.example.artemisconsumer.repositpries.ApiDumpRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class Insert {
    private ApiDumpRepository apiDumpRepository;
    private ApiAuditRepository apiAuditRepository;
    private  List<ApiDumpEntity> apiDumpEntityList ;
    private List<ApiAuditEntity> apiAuditEntityList ;
    public void insert(ApiDumpRepository apiDumpRepository,
                       ApiAuditRepository apiAuditRepository,
                       List<ApiDumpEntity> apiDumpEntityList,
                       List<ApiAuditEntity> apiAuditEntityList){
        this.apiDumpRepository = apiDumpRepository;
        this.apiAuditRepository = apiAuditRepository;
        this.apiDumpEntityList = new ArrayList<>(apiDumpEntityList) ;
        this.apiAuditEntityList = new ArrayList<>(apiAuditEntityList);

        Timestamp t1 = new Timestamp(new Date().getTime());
        apiDumpRepository.saveAll(apiDumpEntityList);
        apiAuditRepository.saveAll(apiAuditEntityList);
        Timestamp t2 = new Timestamp(new Date().getTime());
        System.out.println("time before insertion "+t1);
        System.out.println("time after insertion "+t2);
        System.out.println("Batch inserting in  " + (t2.getTime() - t1.getTime())/1000.0 + " s");
    }
}
