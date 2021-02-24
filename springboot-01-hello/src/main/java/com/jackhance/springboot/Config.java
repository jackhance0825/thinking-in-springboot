package com.jackhance.springboot;

import com.jackhance.springboot.controller.HelloController;
import com.jackhance.springboot.pojo.CarProperties;
import com.jackhance.springboot.pojo.Pet;
import com.jackhance.springboot.pojo.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * proxyBeanMethods :
 * Full(proxyBeanMethods = true)、【保证每个@Bean方法被调用多少次返回的组件都是单实例的】
 * Lite(proxyBeanMethods = false)【每个@Bean方法被调用多少次返回的组件都是新创建的】
 * 组件依赖必须使用Full模式默认。其他默认是否Lite模式
 */
@Configuration(proxyBeanMethods = false)
/**
 * application.properties导入自定义properties的方式：(配置文件：application.properties & application.yml)
 * 1. properties类声明 @ConfigurationProperties(prefix = "XXX") & @Component
 * 2. properties类声明 @ConfigurationProperties(prefix = "XXX") & 配置类声明@EnableConfigurationProperties(properties类)
 */
@EnableConfigurationProperties(CarProperties.class)
public class Config {

    @Bean
    public User user01() {
        return new User("jackhance", 10);
    }

    /**
     * 条件装配： @ConditionOn*
     */
    @Bean
    @ConditionalOnBean(HelloController.class)
    public User user02(Pet p) {
        return new User("jackhance", 10, p);
    }

    @Bean
    public Pet pet01() {
        return new Pet("cat");
    }

}
