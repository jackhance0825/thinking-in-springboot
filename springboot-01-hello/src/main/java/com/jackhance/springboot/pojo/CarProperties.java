package com.jackhance.springboot.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

//@Component
@ConfigurationProperties(prefix = "car")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarProperties {
    private String brand;
    private Integer price;
}
