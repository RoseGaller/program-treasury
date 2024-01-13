package com.lct;


import com.lct.study.UserApplication;
import com.lct.study.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UserApplication.class})
public class ApplicationZKTest {

    @Autowired
    private UserService userService;

    @Test
    public void testController() throws Exception {
        System.out.println(userService);
    }
}
