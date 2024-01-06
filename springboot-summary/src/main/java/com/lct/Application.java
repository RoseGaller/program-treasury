package com.lct;

import com.lct.filter.SomeFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
//注解式 添加过滤器
@ServletComponentScan("com.lct.filter")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

    //配置式 添加过滤器
    @Bean
    public FilterRegistrationBean<SomeFilter> getFilteBean(){
        SomeFilter someFilter = new SomeFilter();
        FilterRegistrationBean<SomeFilter> registrationBean = new FilterRegistrationBean<>(someFilter);
        registrationBean.addUrlPatterns("/*");
        return  registrationBean;
    }
}
