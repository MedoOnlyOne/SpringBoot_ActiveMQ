package com.example.artemisconsumer;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.artemisconsumer.models.ApiAuditEntity;
import com.example.artemisconsumer.models.ApiDumpEntity;
import com.example.artemisconsumer.repositpries.ApiAuditEntityRepository;
import com.example.artemisconsumer.repositpries.ApiDumpEntityRepository;

@Component
public class RunnableObject implements Runnable {
	
	@Autowired
	private ApiAuditEntityRepository apiAuditEntityRepository;
	@Autowired
	private ApiDumpEntityRepository  apiDumpEntityRepository;
	
	private List<ApiDumpEntity> apiDumpEntityList ;
	private ApiAuditEntity apiAuditEntity;

	public RunnableObject() {}
	public RunnableObject(
			ApiAuditEntityRepository apiAuditEntityRepository ,
			ApiAuditEntity apiAuditEntity,
			ApiDumpEntityRepository  apiDumpEntityRepository ,
			List<ApiDumpEntity> apiDumpEntityList) {
		this.apiDumpEntityRepository =  apiDumpEntityRepository;
		this.apiAuditEntityRepository = apiAuditEntityRepository;
		this.apiAuditEntity = apiAuditEntity;
		this.apiDumpEntityList = new ArrayList<>(apiDumpEntityList);
	}

//	public RunnableObject(ApiAuditEntity apiAuditEntity, List<ApiDumpEntity> apiDumpEntityList) {
//		this.apiAuditEntity = apiAuditEntity;
//		this.apiDumpEntityList = new ArrayList<>(apiDumpEntityList);
//	}

	@Override
	public void run() {
		System.out.println("Started thread");

		// Save Audit message & dump
		Timestamp t1 = new Timestamp(new Date().getTime());
		apiAuditEntityRepository.save(apiAuditEntity);
		apiDumpEntityRepository.saveAll(apiDumpEntityList);
		Timestamp t2 = new Timestamp(new Date().getTime());

		System.out.println("time before insertion "+t1);
		System.out.println("time after insertion "+t2);
		System.out.println("Ended thread");
	}
}
