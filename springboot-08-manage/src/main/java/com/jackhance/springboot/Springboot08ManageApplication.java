package com.jackhance.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class Springboot08ManageApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(Springboot08ManageApplication.class, args);
		ConfigurableEnvironment evn = applicationContext.getEnvironment();
		System.out.println(evn.getSystemEnvironment());
		System.out.println(evn.getSystemProperties());
	}

}
