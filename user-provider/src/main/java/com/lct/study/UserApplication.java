package com.lct.study;


import com.lct.study.health.CustomHealthIndicator;
import org.dromara.dynamictp.core.spring.EnableDynamicTp;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@SpringBootApplication
@EnableTransactionManagement
@EnableAspectJAutoProxy
@EnableRetry

//1、开启动态线程池 2、配置文件：spring.dynamic.tp.enabled=true
@EnableDynamicTp
public class UserApplication implements ApplicationContextAware {

    public ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class,args);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @RestController
    public class TestController {

        @Autowired
        private CustomHealthIndicator customHealthIndicator;

        @GetMapping("/updateStatus")
        public String updateStatus(boolean status) {
            customHealthIndicator.setStatus(status);
            return "success";
        }
    }

}
