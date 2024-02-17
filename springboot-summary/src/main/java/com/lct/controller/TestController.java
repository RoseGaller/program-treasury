package com.lct.controller;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;
import com.lct.advice.NoAPIResponse;
import com.lct.bean.APIResponse;
import com.lct.bean.Some;
import com.lct.bean.User;
import com.lct.exception.APIException;
import com.lct.monitor.PrometheusCustomMonitor;
import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
@Slf4j
//Bean 默认是单例的，所以单例的 Controller 注入的 Service 也是一次性创建的，即使Service 本身标识了 prototype 的范围也没用。
//每次直接从 ApplicationContext 中获取Bean

//@PropertySource(value = "classpath:some.properties") //导入外部配置文件
@Api("测试相关接口")
public class TestController{

    private Log logger = LogFactory.getLog(TestController.class);

    @Autowired
    private Some some;

    @Value("${server.port}")
    private String port;

    @Value("${some.name}")
    private String name;

    @Resource
    private PrometheusCustomMonitor prometheusCustomMonitor;


//    @Autowired
//    private WrapService wrapService;

    @GetMapping("/test")
    @ResponseBody
    @NoAPIResponse
    @Timed
    public String test(){
        log.debug("测试MD");
        log.info("参数：port = {},",port);


        TransmittableThreadLocal<String> local = new TransmittableThreadLocal<>();
        local.set("测试ThreadLocal");
        log.debug("Tomcat 线程");
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService = TtlExecutors.getTtlExecutorService(executorService);
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                log.debug("TtlExecutors 线程",local.get());
            }
        });
        return "Hello!  " + port + "  " + some.getName();
    }

    @GetMapping("/getRes/{id}")
    @ResponseBody
    @Timed
    @ApiOperation(value = "查询用户信息",response = APIResponse.class,responseHeaders={})
    public User getRes(@PathVariable("id") Long id){
        if(id.equals(1L)){
            throw  new APIException(500,"未知的异常");
        }
        User user = new User();
        user.setId(id);
        user.setName("hahaha");
        return user;
    }

//    @RequestMapping("/testStarter/{id}")
//    @ResponseBody
//    @NoAPIResponse
//    public String testStarter(@PathVariable("id")String id){
//        return "Hello!  " +wrapService.wrap(id);
//    }
}