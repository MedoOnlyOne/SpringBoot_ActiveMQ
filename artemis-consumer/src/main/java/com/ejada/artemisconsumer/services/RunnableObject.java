package com.ejada.artemisconsumer.services;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ejada.artemisconsumer.models.ApiAuditEntity;
import com.ejada.artemisconsumer.models.ApiDumpEntity;

@Component
public class RunnableObject implements Runnable {
	private Insert insert;
	
	private List<ApiDumpEntity> apiDumpEntityList ;
	private List<ApiAuditEntity> apiAuditEntityList ;
	private List<String> messageList;
	
	public RunnableObject() {}
	public RunnableObject(
			Insert insert,
			List<ApiDumpEntity> apiDumpEntityList,
			List<ApiAuditEntity> apiAuditEntityList,
			List<String> messageList
			)
	{
		this.insert = insert;
		this.apiDumpEntityList = apiDumpEntityList;
		this.apiAuditEntityList = apiAuditEntityList;
		this.messageList = messageList;
	}


	@Override
	public void run() {
		// Save Audit message & dump
		insert.insert(apiDumpEntityList, apiAuditEntityList, messageList);
		ArtemisConsumer.setFreeLists(apiAuditEntityList, apiDumpEntityList, messageList);
	}
}
