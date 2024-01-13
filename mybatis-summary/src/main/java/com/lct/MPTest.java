package com.lct;

import com.baomidou.mybatisplus.core.MybatisSqlSessionFactoryBuilder;
import com.lct.bean.User;
import com.lct.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sun.misc.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class MPTest {

    InputStream inputStream = null;

    SqlSessionFactory sqlSessionFactory = null;

    SqlSession sqlSession = null;

    UserMapper userMapper = null;

    @Before
    public void before() throws IOException {
        inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession = sqlSessionFactory.openSession();
        userMapper = sqlSession.getMapper(UserMapper.class);
    }


    @Test
    public void testMybatis() throws IOException {
        List<User> userList = userMapper.findAll();
        Assert.assertEquals(5,userList.size());
        Assert.assertEquals(6,userList.size());
    }

    @Test
    public void testMybatisInsert() throws IOException {
        User user = new User();
        user.setAge(12);
        user.setEmail("99999@qq.com");
        user.setName("test");

        //返回的result是受影响的行数，并不是自增后的id
        int result = userMapper.insert(user);
        sqlSession.commit();
        System.out.println(result);
        System.out.println(user.getId());
    }


}
