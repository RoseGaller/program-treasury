package com.lct;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.MybatisSqlSessionFactoryBuilder;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lct.KeyGenerator.StaticIKeyGenerator;
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

public class MybatisPlusTest {

    InputStream inputStream = null;

    SqlSessionFactory sqlSessionFactory = null;

    SqlSession sqlSession = null;

    @Before
    public void before() throws IOException {
        //修改UserMapper.java继承BaseMapper
        //MybatisSqlSessionFactoryBuilder
        inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new MybatisSqlSessionFactoryBuilder().build(inputStream);
        MybatisConfiguration mybatisConfiguration = (MybatisConfiguration) sqlSessionFactory.getConfiguration();
        mybatisConfiguration.getGlobalConfig().getDbConfig().setKeyGenerator(new StaticIKeyGenerator());
        sqlSession = sqlSessionFactory.openSession();
    }


    @Test
    public void testFindAll() throws IOException {
        Collection<String> mappedStatementNamesList= sqlSessionFactory.getConfiguration().getMappedStatementNames();
        for(String mappedStatement : mappedStatementNamesList){
            System.out.println(mappedStatement);
        }
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = userMapper.findAll();
        Assert.assertEquals(5,userList.size());
        for(User user: userMapper.selectList(null)){
            System.out.println(user);
        }
    }
    @Test
    public void testInsert() throws IOException {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setAge(12);
        user.setEmail("99999@qq.com");
        user.setName("test");

        //返回的result是受影响的行数，并不是自增后的id
        //默认不是自增的
        int result = userMapper.insert(user);
        System.out.println(result);
        System.out.println(user.getId());
    }

    @Test
    public void testInsertByAutoId() throws IOException {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setAge(12);
        user.setEmail("99999@qq.com");
        user.setName("test");

        //在实体的主键添加@TableId(type = IdType.AUTO) //指定id类型为自增⻓
        int result = userMapper.insert(user);
        System.out.println(result);
        System.out.println(user.getId());
    }

    @Test
    public void testUpdateByAutoId() throws IOException {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setAge(22);
        user.setEmail("99999@qq.com");
        user.setName("test");

        //更新的条件
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",5);

        //执行更新操作
        int result = userMapper.update(user,queryWrapper);
        sqlSession.commit(true);
        System.out.println(result);
        System.out.println(user.getId());
    }

}
