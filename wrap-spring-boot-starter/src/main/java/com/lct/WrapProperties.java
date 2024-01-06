package com.lct;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

//@Configuration
@Data
//@PropertySource(value = "classpath:wrap.properties")
@ConfigurationProperties(prefix = "wrap")
public class WrapProperties {

    private boolean enable;

    private  String before;

    private String after;
}
