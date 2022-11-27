package com.example.artemisconsumer;

import java.util.List;

import com.example.artemisconsumer.repositpries.ApiAuditRepository;
import com.example.artemisconsumer.repositpries.ApiDumpRepository;
import com.example.artemisconsumer.services.Insert;
import org.springframework.stereotype.Component;

import com.example.artemisconsumer.models.ApiAuditEntity;
import com.example.artemisconsumer.models.ApiDumpEntity;

@Component
public class RunnableObject implements Runnable {
	private ApiDumpRepository apiDumpRepository;
	private ApiAuditRepository apiAuditRepository;

	private Insert insert;
	
	private List<ApiDumpEntity> apiDumpEntityList ;
	private List<ApiAuditEntity> apiAuditEntityList ;
	
	public RunnableObject() {}
	public RunnableObject(
			Insert insert,
			ApiDumpRepository apiDumpRepository,
			ApiAuditRepository apiAuditRepository,
			List<ApiDumpEntity> apiDumpEntityList,
			List<ApiAuditEntity> apiAuditEntityList
			)
	{
		this.insert = insert;
		this.apiDumpRepository = apiDumpRepository;
		this.apiAuditRepository = apiAuditRepository;
		this.apiDumpEntityList = apiDumpEntityList;
		this.apiAuditEntityList = apiAuditEntityList;
	}


	@Override
	public void run() {
		// Save Audit message & dump
		insert.insert(apiDumpRepository, apiAuditRepository, apiDumpEntityList, apiAuditEntityList);
		ArtemisConsumer.setFreeLists(apiAuditEntityList, apiDumpEntityList);
	}
}
