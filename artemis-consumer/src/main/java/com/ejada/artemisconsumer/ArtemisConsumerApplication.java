package com.ejada.artemisconsumer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.ejada.artemisconsumer.repositpries.ApiAuditRepository;
import com.ejada.artemisconsumer.repositpries.ApiDumpRepository;
import com.ejada.artemisconsumer.services.Insert;
import com.ejada.artemisconsumer.services.InsertionAfterWaiting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class ArtemisConsumerApplication implements CommandLineRunner {
	@Autowired
    Insert insert;
	@Autowired
    ApiDumpRepository apiDumpRepository;
	@Autowired
    ApiAuditRepository apiAuditRepository;

	public static void main(String[] args) {
		SpringApplication.run(ArtemisConsumerApplication.class, args);
	}

	@Override
	public void run(String... args) {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		InsertionAfterWaiting insertionAfterWaiting = new InsertionAfterWaiting(insert, apiAuditRepository, apiDumpRepository);
		executor.execute(insertionAfterWaiting);
	}
}
