package com.jackhance.springboot.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;



public interface Person {

   String getName();
   Integer getAge();

}
