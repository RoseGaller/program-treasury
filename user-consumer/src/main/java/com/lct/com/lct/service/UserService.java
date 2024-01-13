package com.lct.com.lct.service;


import com.lct.config.FeignConfiguration;
import com.lct.study.bean.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//指定服务的配置信息
@FeignClient(name="user-provider",configuration = FeignConfiguration.class)
public interface UserService {

    @RequestMapping(value = "/user/get/{userId}",method=
            RequestMethod.GET)
    User getUser(@PathVariable("userId") String id);
}
