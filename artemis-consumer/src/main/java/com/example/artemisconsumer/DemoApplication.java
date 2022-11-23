package com.example.artemisconsumer;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.example.artemisconsumer.repositpries.ApiAuditRepository;
import com.example.artemisconsumer.repositpries.ApiDumpRepository;
import com.example.artemisconsumer.services.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	@Autowired
	Insert insert;
	@Autowired
	ApiDumpRepository apiDumpRepository;
	@Autowired
	ApiAuditRepository apiAuditRepository;

	public static void main(String[] args) {
		
		
		/*ExecutorService executorService = Executors.newFixedThreadPool(2);
		for (int i = 0; i < 10; i++) {
			Runnable runnable = new RunnableObject("myThread_" + i);
			executorService.execute(runnable);
		}	
		executorService.shutdown();*/
		
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		InsertionAfterWaiting insertionAfterWaiting = new InsertionAfterWaiting(insert, apiAuditRepository, apiDumpRepository);
		executor.execute(insertionAfterWaiting);
	}
}
