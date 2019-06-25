package com.wzd.reportSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="com.wzd")
public class DataCollectApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataCollectApplication.class, args);
	}

}
