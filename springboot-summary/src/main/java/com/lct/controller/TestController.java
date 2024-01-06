package com.lct.controller;

import com.lct.WrapService;
import com.lct.bean.Some;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
//导入外部配置文件
//@PropertySource(value = "classpath:some.properties")
public class TestController{

    private Log logger = LogFactory.getLog(TestController.class);

    @Autowired
    private Some some;

    @Value("${server.port}")
    private String port;

    @Value("${some.name}")
    private String name;


    @Autowired
    private WrapService wrapService;

    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        log.debug("测试MD");
        return "Hello!  " + port + "  " + some.getName();
    }

    @RequestMapping("/testStarter/{id}")
    @ResponseBody
    public String testStarter(@PathVariable("id")String id){
        return "Hello!  " +wrapService.wrap(id);
    }
}