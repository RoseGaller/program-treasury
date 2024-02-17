package com.lct.study.controller;

import com.lct.study.bean.User;
import com.lct.study.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.ActiveSpan;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
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

   @RequestMapping("/get/{id}")
   public User getById(@PathVariable("id") String id) throws Exception {
       log.debug("traceId:{}",TraceContext.traceId());
       return userService.getById(id);
   }

   @RequestMapping("/err")
   public void  insert() throws Exception {
       log.debug("traceId:{}",TraceContext.traceId());
       ActiveSpan.tag("error-trace activation","error");
       ActiveSpan.error();
       throw new RuntimeException("err");
    }
}
