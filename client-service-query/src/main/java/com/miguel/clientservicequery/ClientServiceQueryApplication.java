package com.miguel.clientservicequery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ClientServiceQueryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientServiceQueryApplication.class, args);
	}

}
