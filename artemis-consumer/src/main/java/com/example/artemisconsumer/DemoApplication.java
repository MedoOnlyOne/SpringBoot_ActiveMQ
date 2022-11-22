package com.example.artemisconsumer;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		
		
		/*ExecutorService executorService = Executors.newFixedThreadPool(2);
		for (int i = 0; i < 10; i++) {
			Runnable runnable = new RunnableObject("myThread_" + i);
			executorService.execute(runnable);
		}	
		executorService.shutdown();*/
		
		SpringApplication.run(DemoApplication.class, args);
	}

}
