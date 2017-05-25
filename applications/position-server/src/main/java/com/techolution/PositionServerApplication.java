package com.techolution;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PositionServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PositionServerApplication.class, args);
	}
}
