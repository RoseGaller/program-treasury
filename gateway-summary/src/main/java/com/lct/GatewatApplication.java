package com.lct;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class GatewatApplication {
    public static void main(String[] args) {
        System.setProperty("reactor.netty.http.server.accessLogEnabled","true");
        SpringApplication.run(GatewatApplication.class,args);
    }
}
