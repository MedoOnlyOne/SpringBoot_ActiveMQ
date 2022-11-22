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
	private ApiAuditEntityRepository APIAuditEntityRepository;
	
	@Autowired
	private ApiDumpEntityRepository  APIDumpEntityRepository;
	
	private List<ApiDumpEntity> apiDumpEntityList ;
	private ApiAuditEntity ApiAuditEntity;
	
	public RunnableObject() {}
	public RunnableObject(
			ApiAuditEntityRepository APIAuditEntityRepository ,
			ApiAuditEntity ApiAuditEntity, 
			ApiDumpEntityRepository  APIDumpEntityRepository , 
			List<ApiDumpEntity> apiDumpEntityList
			)
	{
		this.APIDumpEntityRepository =  APIDumpEntityRepository;
		this.APIAuditEntityRepository = APIAuditEntityRepository;
		this.ApiAuditEntity = ApiAuditEntity ;
		this.apiDumpEntityList = apiDumpEntityList;
	}
	String name;
	
	public RunnableObject(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		System.out.println("Started thread " + name);
		//save db
          /* ApiAuditEntity ApiAuditEntity= APIAuditEntityRepository.findById(892).orElseThrow(
			() ->  new IllegalStateException("Student with id " + 892 + " does not exist")
			 );
         System.out.print(ApiAuditEntity.getaApiName());*/
		// Save Audit message & dump 
		Timestamp t1 = new Timestamp(new Date().getTime());
		APIAuditEntityRepository.save(ApiAuditEntity);
		APIDumpEntityRepository.saveAll(apiDumpEntityList);
		Timestamp t2 = new Timestamp(new Date().getTime());
		 System.out.println("time before insertion "+t1);
		 System.out.println("time after insertion "+t2);
		System.out.println("Ended thread " + name);
	}
}
