package com.lct;


import com.lct.rule.CustomRule;
import com.netflix.loadbalancer.IRule;
import feign.Client;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
//启用feign，指定扫描的包，创建代理
@EnableFeignClients(basePackages = {"com.lct.service"})
public class UserApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext ss = SpringApplication.run(UserApplication.class,args);
        FeignClientProperties  feignClientConfiguration = ss.getBean(FeignClientProperties.class) ;
        System.out.println(feignClientConfiguration);

        Client client =  ss.getBean(Client.class);
        System.out.println(client);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }
}
