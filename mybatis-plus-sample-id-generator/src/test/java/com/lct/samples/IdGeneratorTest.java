package com.lct.samples;

import com.lct.samples.entity.User;
import com.lct.samples.mapper.UserMapper;
import com.lct.samples.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IdGeneratorTest {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;

    @Test
    public void test() {
        User user = new User();
        user.setName("靓仔");
        user.setAge(18);
        userMapper.insert(user);
        System.out.println(user);
    }
}
