package com.organicautonomy.reviewapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class ReviewApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReviewApiApplication.class, args);
	}

}
