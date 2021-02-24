package com.jackhance.springboot.config;

import com.jackhance.springboot.model.Color;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ProfileConfig {

    @Profile("prod")
    @Bean
    public Color red(){
        return new Color("red");
    }

    @Profile("test")
    @Bean
    public Color green(){
        return new Color("green");
    }
}
