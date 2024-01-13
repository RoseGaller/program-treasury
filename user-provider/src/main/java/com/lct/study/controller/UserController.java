package com.lct.study.controller;

import com.lct.study.bean.User;
import com.lct.study.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {


   //命名时，一定要注意不仅要避免和环境变量冲突，也要注意避免和系统变量等其他变量冲突不仅要避免和环境变量冲突
//   @Value("${spring.application.name}")
//   public String applicationName1;
//
//   @Value("default")
//   public String applicationName2;
//
//    @Value("#{bean.name}")
//    public String applicationName3;

    @Autowired
   private UserService userService;

   @GetMapping("/get/{id}")
   @ResponseBody
   public User getById(@PathVariable("id") String id) throws Exception {
       log.debug("8884");
       return userService.getById(id);
   }

   @GetMapping("/insert")
   public void  insert() throws Exception {
       userService.insert();
    }
}
