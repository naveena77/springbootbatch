package com.spring.batchpoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
// enables asyn job execution
@EnableAsync
// enables  to shedule the job
@EnableScheduling
public class BatchpocApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatchpocApplication.class, args);
	}

}
