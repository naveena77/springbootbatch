package com.springreader.xlsxtodatasource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class XlsxtodatasourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(XlsxtodatasourceApplication.class, args);
	}

}
