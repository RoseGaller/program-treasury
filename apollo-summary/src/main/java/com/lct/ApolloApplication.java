package com.lct;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@SpringBootApplication
public class ApolloApplication {

    public static void main(String[] args) {
        new SpringApplication(ApolloApplication.class).run(args);
        Config config = ConfigService.getConfig("test-apollo");
        Set<String>  nameSet = config.getPropertyNames();
        for(String name : nameSet){
            System.out.println(name);
        }
    }

    @RestController
    public class TestController {

        //apollo配置中心修改之后，此处自动修改
        //ConfigUtil中 private boolean autoUpdateInjectedSpringProperties = true;
        @Value("${timeout:1000}")
        private int timeout;

        @GetMapping("/test")
        public int test() {
            return timeout;
        }
    }
}
