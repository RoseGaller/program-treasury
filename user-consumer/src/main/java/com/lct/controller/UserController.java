package com.lct.controller;

import com.lct.com.lct.service.UserService;
import com.lct.study.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

//@Controller
//@RequestMapping("/user")
@RestController("/user")
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private UserService userService;

    @RequestMapping("/get/{id}")
//    @ResponseBody
    public User getUser(@PathVariable("id") String id){
        //根据服务名称通过discoveryClient从注册中心拉取服务提供者实例
        List<ServiceInstance>  serviceInstanceList = discoveryClient.getInstances("user-provider");
        //根据服务名+请求路径通过restTemplate发送http请求
        //当使用RestTemplate组装表单数据时，我们应该注意要使用MultiValueMap而非普通的HashMap。否则会以JSON请求体的形式发送请求而非表
//        String url = "http://USER-PROVIDER/user/get/48";
//        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
//        URI uri = builder.build().encode().toUri();
        //======================================================
        //protocol://hostname[:port]/path/[?query]#fragment
        //注意URL中含有特殊字符的情况，UriComponentsBuilder.fromHttpUrl和UriComponentsBuilder.fromUriString会解析query和fragment
//        ResponseEntity<User> responseEntity =  restTemplate.getForEntity("http://user-provider/user/get/48",User.class);
//        return responseEntity.getBody();
        return userService.getUser(id);
    }

}
