package com.example.artemisconsumer;

import java.util.ArrayList;
import java.util.List;

import com.example.artemisconsumer.repositpries.ApiAuditRepository;
import com.example.artemisconsumer.repositpries.ApiDumpRepository;
import com.example.artemisconsumer.services.Insert;
import org.springframework.stereotype.Component;

import com.example.artemisconsumer.models.ApiAuditEntity;
import com.example.artemisconsumer.models.ApiDumpEntity;
import com.example.artemisconsumer.repositpries.ApiAuditEntityRepository;
import com.example.artemisconsumer.repositpries.ApiDumpEntityRepository;

@Component
public class RunnableObject implements Runnable {
	private ApiAuditEntityRepository apiAuditEntityRepository;
	private ApiDumpEntityRepository  apiDumpEntityRepository;
	private ApiDumpRepository apiDumpRepository;
	private ApiAuditRepository apiAuditRepository;

	private Insert insert;
	
	private List<ApiDumpEntity> apiDumpEntityList ;
	private List<ApiAuditEntity> apiAuditEntityList ;
	private int activeList;
	
	public RunnableObject() {}
	public RunnableObject(
			Insert insert,
			ApiDumpRepository apiDumpRepository,
			ApiAuditRepository apiAuditRepository,
			List<ApiDumpEntity> apiDumpEntityList,
			List<ApiAuditEntity> apiAuditEntityList,
			int activelist
			)
	{
		this.insert = insert;
		this.apiDumpRepository = apiDumpRepository;
		this.apiAuditRepository = apiAuditRepository;
		this.apiDumpEntityList = apiDumpEntityList;
		this.apiAuditEntityList = apiAuditEntityList;
		this.activeList = activelist;
	}


	@Override
	public void run() {
		// Save Audit message & dump
		insert.insert(apiDumpRepository, apiAuditRepository, apiDumpEntityList, apiAuditEntityList);
		ArtemisConsumer.setFreeLists(activeList);
	}
}
