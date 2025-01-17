package com.jackhance.springboot.controller;

import com.jackhance.springboot.model.Color;
import com.jackhance.springboot.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProfileController {
    @Autowired
    Color color;

    @ResponseBody
    @RequestMapping("/color")
    public Color color() {
        return color;
    }

    @Value("${person.name:李四}")
    private String name;


    @Autowired
    private Person person;

    @Value("${MAVEN_HOME}")
    private String msg;

    @Value("${os.name}")
    private String osName;

    @GetMapping("/")
    public String hello(){

        return person.getClass().toString();
    }

    @GetMapping("/person")
    public Person person(){

        return person;
    }

    @GetMapping("/msg")
    public String getMsg(){
        return msg+"==>"+osName;
    }

}
