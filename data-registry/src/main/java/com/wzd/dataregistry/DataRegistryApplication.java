package com.wzd.dataregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer//标记自己是注册中心
public class DataRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataRegistryApplication.class, args);
	}

}
