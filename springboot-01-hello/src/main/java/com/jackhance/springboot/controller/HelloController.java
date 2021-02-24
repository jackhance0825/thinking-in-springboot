package com.jackhance.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello(String name) {
        return "Spring Boot Hello World! 你好, "+name;
    }
}
