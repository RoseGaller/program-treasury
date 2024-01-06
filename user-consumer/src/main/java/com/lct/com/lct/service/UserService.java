package com.lct.com.lct.service;


import com.lct.study.bean.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="user-provider")
public interface UserService {

    @RequestMapping(value = "/user/get/{userId}",method=
            RequestMethod.GET)
    User getUser(@PathVariable("userId") String id);
}
