package com.lct.study.service.impl;

import com.lct.study.bean.User;
import com.lct.study.dao.UserMapper;
import com.lct.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component
public class UserServiceImpl  implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getById(String id) {
        return userMapper.selectByPrimaryKey(id);
    }


    //默认传播机制是REQUIRED，它的含义是：如果本来有事务，则加入该事务，如果没有事务，则创建新的事务
    //在整个事务的调用链上，任何一个环节抛出的异常都会导致全局回滚
    //=========================================================
    //REQUIRES_NEW每次都会新建一个新的事务，内部事务的回滚不会影响外部事务的提交回滚。常用于外部事务重要，内部事务不重要的场景
    //=========================================================
    //@Transactional对private方法不生效，应该把需要支持事务的方法声明为public类型
    //=========================================================
    //RuntimeException是Exception的子类，如果用rollbackFor=Exception.class，那对RuntimeException
    //也会生效。如果我们需要对Exception执行回滚操作，但对于RuntimeException不执行回滚操作,可以配置
    //@Transactional(rollbackFor=Exception.class,noRollbackFor=RuntimeException.class)
    @Override
//    @Transactional(rollbackFor = Exception.class)
    @Transactional
    public void  insert() throws Exception {
        //spring在处理事务时，如果没有在Transactional中配置rollback属性
        //那么只有捕获到RuntimeException或者Error的时候才会触发回滚操作
        //而此处抛出的异常是Exception，又没有指定与之匹配的回滚规则，所以不会触发回滚
        User user = new User();
        user.setId("111111111");
        user.setUsername("test1111111");
        user.setPassword("1");
        user.setUtype("1");
        user.setStatus("1");
        user.setCreateTime(new Date());
        user.setName("test");
        userMapper.insert(user);
        throw  new Exception("用户已经存在");
    }

}
