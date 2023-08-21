package com.company.recordstoreapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableResourceServer
public class RecordStoreApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecordStoreApiApplication.class, args);
	}

}
