package com.jackhance.springboot;

import com.jackhance.springboot.pojo.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
/**
 * 绑定效率较低，建议使用@ConfigurationProperties
 */
@PropertySource(value = "classpath:/my.properties", encoding = "utf-8")
public class Springboot02ConfigApplication {

    @Value("${animal}")
    private String animal;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Springboot02ConfigApplication.class, args);
        Person person = context.getBean(Person.class);
        System.out.println(person);

        System.out.println("============================");
        Springboot02ConfigApplication springboot02ConfigApplication = context.getBean(Springboot02ConfigApplication.class);
        System.out.println(springboot02ConfigApplication.animal);
    }

}
