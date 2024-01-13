package com.lct;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.MybatisSqlSessionFactoryBuilder;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.parsers.BlockAttackSqlParser;
import com.baomidou.mybatisplus.extension.plugins.SqlExplainInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lct.KeyGenerator.StaticIKeyGenerator;
import com.lct.bean.User;
import com.lct.fill.CustomMetaObjectHandler;
import com.lct.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Slf4j
public class MybatisPlusTest {

    InputStream inputStream = null;

    SqlSessionFactory sqlSessionFactory = null;

    SqlSession sqlSession = null;

    MybatisConfiguration mybatisConfiguration = null;

    @Before
    public void before() throws IOException, SQLException {
        //修改UserMapper.java继承BaseMapper
        //MybatisSqlSessionFactoryBuilder
        inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new MybatisSqlSessionFactoryBuilder().build(inputStream);
        mybatisConfiguration = (MybatisConfiguration) sqlSessionFactory.getConfiguration();
//        mybatisConfiguration.getGlobalConfig().getDbConfig().setKeyGenerator(new StaticIKeyGenerator());
        sqlSession = sqlSessionFactory.openSession();
        sqlSession.getConnection().setAutoCommit(true);
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
    public void testFindPage() throws IOException {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //分页查询
        //SELECT COUNT(1) FROM user WHERE name = ?
        //SELECT id,name,age,email FROM user WHERE name = ? LIMIT ?,?
        //需要添加分页插件
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("name","test");
        Page page = new Page<>(0, 5);
        Page<User> selectPage = (Page<User>) userMapper.selectPage(page,queryWrapper);
        List<User> users = selectPage.getRecords();
        for(User user: users){
            System.out.println(user);
        }
    }

    @Test
    public void tesOptional() throws IOException {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("name","test666");
        User user = userMapper.selectOne(queryWrapper);
        Optional<User> userOptional = Optional.ofNullable(user);
        System.out.println(userOptional.isPresent()?userOptional.get().getName():"default");

    }

    @Test
    public void testCount() throws IOException {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("name","te2st");
        //没有符合条件的，返回0
        Integer count = userMapper.selectCount(queryWrapper);
        System.out.println("用户数： " + count );
    }
    @Test
    public void testInsert() throws IOException {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
       //mybatis-plus会根据输入的对象的字段的个数来动态的调整sql语句插入的字段
//        user.setAge(12);  insert into user ( name, email ) values ( ?, ? )
        user.setEmail("9999912@qq.com");
        user.setName("test12");

        //返回的result是受影响的行数，并不是自增后的id
        int result = userMapper.insert(user);
        System.out.println(result);
        System.out.println(user.getId());
    }

    @Test
    public void testCustomIdInsert() throws IOException {
        mybatisConfiguration = (MybatisConfiguration) sqlSessionFactory.getConfiguration();
        mybatisConfiguration.getGlobalConfig().setIdentifierGenerator(new StaticIKeyGenerator());
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
//        user.setId(1L);
        //mybatis-plus会根据输入的对象的字段的个数来动态的调整sql语句插入的字段
//        user.setAge(12);  insert into user ( name, email ) values ( ?, ? )
        user.setEmail("9999912@qq.com");
        user.setName("test123");

        //返回的result是受影响的行数，并不是自增后的id
        int result = userMapper.insertByAutoId(user);
        System.out.println(result);
        System.out.println(user.getId());
    }

    @Test
    public void testAutoFillInsert() throws IOException {

        mybatisConfiguration = (MybatisConfiguration) sqlSessionFactory.getConfiguration();
        //公共字段填充
        mybatisConfiguration.getGlobalConfig().setMetaObjectHandler(new CustomMetaObjectHandler());
        sqlSession =  sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        //mybatis-plus会根据输入的对象的字段的个数来动态的调整sql语句插入的字段
//        user.setAge(12);  insert into user ( name, email ) values ( ?, ? )
        user.setEmail("9999912@qq.com");
        user.setName("test12");

        //返回的result是受影响的行数，并不是自增后的id
        int result = userMapper.insert(user);
        sqlSession.commit();
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

    @Test
    public void testSqlExplain(){
        //SQL执行分析插件，避免出现全表更新和删除
        SqlExplainInterceptor sqlExplainInterceptor = new SqlExplainInterceptor();
        BlockAttackSqlParser blockAttackSqlParser = new BlockAttackSqlParser();
        List<ISqlParser> sqlParser = new ArrayList<>();
        sqlParser.add(blockAttackSqlParser);
        sqlExplainInterceptor.setSqlParserList(sqlParser);

        mybatisConfiguration = (MybatisConfiguration) sqlSessionFactory.getConfiguration();
        mybatisConfiguration.addInterceptor(sqlExplainInterceptor);
        sqlSession =  sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        int delete = userMapper.delete(null);
        System.out.println(delete);
    }

}
