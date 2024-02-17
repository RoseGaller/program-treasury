package com.lct;

import com.lct.filter.LogbackFilter;
import com.lct.filter.SomeFilter;
import com.lct.utils.SpringUtil;
import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.jdbc.DataSourcePoolMetricsAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, DataSourcePoolMetricsAutoConfiguration.class })
//注解式 添加过滤器
@ServletComponentScan("com.lct.filter")
//@EnableAutoConfiguration
//@ComponentScan(basePackages="com.lct")
//@ImportResource("xxx.xml")
//@EnableRetry
@EnableWebMvc
@EnableAspectJAutoProxy
public class Application {
    public static void main(String[] args) {
        System.setProperty("LOAD_CHAOS_MONKEY","true");
        SpringApplication application = new SpringApplication(Application.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }

    //配置式 添加过滤器
    @Bean
    public FilterRegistrationBean<SomeFilter> getFilteBean(){
        SomeFilter someFilter = new SomeFilter();
        FilterRegistrationBean<SomeFilter> registrationBean = new FilterRegistrationBean<>(someFilter);
        registrationBean.addUrlPatterns("/*");
        return  registrationBean;
    }

    //配置式 添加过滤器
    @Bean
    public FilterRegistrationBean<SomeFilter> logbackFilter(ApplicationContext applicationContext){
        LogbackFilter logbackFilter = new LogbackFilter();
        logbackFilter.setApplicationContext(applicationContext);
        FilterRegistrationBean<SomeFilter> registrationBean = new FilterRegistrationBean(logbackFilter);
        registrationBean.addUrlPatterns("/*");
        return  registrationBean;
    }

    @Bean
    public TimedAspect timedAspect(MeterRegistry registry){
        TimedAspect timedAspect = new TimedAspect(registry);
        return  timedAspect;
    }
}
