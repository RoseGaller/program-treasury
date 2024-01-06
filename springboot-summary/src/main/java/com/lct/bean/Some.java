package com.lct.bean;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.Serializable;


@Data
@Configuration
@PropertySource(value = "classpath:some.properties")
@ConfigurationProperties(prefix = "some") //对配置集中管理
public class Some implements Serializable {

    private String name;
    private  int age;
}
