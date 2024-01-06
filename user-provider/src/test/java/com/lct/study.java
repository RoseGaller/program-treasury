package com.lct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = study.class)
public class study {


//    @Autowired
//    public HelloController helloController;

    @Autowired
    public HelloWorldService helloWorldService;

    @Test
    public void testController() throws Exception {
        System.out.println(helloWorldService);
//        String response = helloController.hi();
//        Assert.notNull(response, "not null");
    }
}
