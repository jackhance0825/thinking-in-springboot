package com.jackhance.springboot.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * application.properties导入自定义properties的方式：(配置文件：application.properties & application.yml)
 * 1. properties类声明 @ConfigurationProperties(prefix = "XXX") & @Component
 * 2. properties类声明 @ConfigurationProperties(prefix = "XXX") & 配置类声明@EnableConfigurationProperties(properties类)
 */
@ConfigurationProperties(prefix = "person")
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private String userName;
    private Boolean boss;
    private Date birth;
    private Integer age;
    private Pet pet;
    private String[] interests;
    private List<String> animal;
    private Map<String, Object> score;
    private Set<Double> salarys;
    private Map<String, List<Pet>> allPets;
}
