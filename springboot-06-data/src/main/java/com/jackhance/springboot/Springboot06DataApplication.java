package com.jackhance.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@MapperScan("com.jackhance.springboot.mapper")
@SpringBootApplication
public class Springboot06DataApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot06DataApplication.class, args);
	}

}
