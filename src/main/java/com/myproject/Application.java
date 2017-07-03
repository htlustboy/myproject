package com.myproject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Application {

	private static Logger logger = LoggerFactory.getLogger(Application.class);
	
	public static void main(String[] args) {
		logger.info("程序开始启动~~~~~~~~~~~~~~~~");
		
		SpringApplication.run(Application.class, args);
		
		logger.info("程序启动完毕~~~~~~~~~~~~~~~~");
		logger.info("项目启动路径：localhost:8090/index");
		
	}
}
