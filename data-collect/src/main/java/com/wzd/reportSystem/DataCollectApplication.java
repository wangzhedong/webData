package com.wzd.reportSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages="com.wzd")
@EnableDiscoveryClient
public class DataCollectApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataCollectApplication.class, args);
	}

}
