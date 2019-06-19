package com.wzd.reportSystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wzd.core.mapper")
public class DataCollectApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataCollectApplication.class, args);
	}

}
