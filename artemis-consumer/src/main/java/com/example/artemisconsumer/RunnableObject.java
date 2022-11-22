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
import com.example.artemisconsumer.repositpries.ApiAuditRepository;
import com.example.artemisconsumer.repositpries.ApiDumpEntityRepository;
import com.example.artemisconsumer.repositpries.ApiDumpRepository;

@Component
public class RunnableObject implements Runnable {
	
	@Autowired
	private ApiAuditEntityRepository APIAuditEntityRepository;
	
	@Autowired
	private ApiDumpEntityRepository  APIDumpEntityRepository;
	
	private List<ApiDumpEntity> apiDumpEntityList ;
	private List<ApiAuditEntity> ApiAuditEntityList ;
	
	
	public RunnableObject() {}
	public RunnableObject(
			ApiAuditEntityRepository APIAuditEntityRepository ,
			ApiDumpEntityRepository  APIDumpEntityRepository , 
			List<ApiDumpEntity> apiDumpEntityList, 
			List<ApiAuditEntity> ApiAuditEntityList
			)
	{
		this.APIDumpEntityRepository =  APIDumpEntityRepository;
		this.APIAuditEntityRepository = APIAuditEntityRepository;
		this.apiDumpEntityList = new ArrayList<>(apiDumpEntityList) ;
		this.ApiAuditEntityList = new ArrayList<>(ApiAuditEntityList); 
	}
	

	@Override
	public void run() {
		// Save Audit message & dump 
		Timestamp t1 = new Timestamp(new Date().getTime());
		APIAuditEntityRepository.saveAll(ApiAuditEntityList);
		APIDumpEntityRepository.saveAll(apiDumpEntityList);		
		Timestamp t2 = new Timestamp(new Date().getTime());
		 System.out.println("time before insertion "+t1);
		 System.out.println("time after insertion "+t2);
	}
}
