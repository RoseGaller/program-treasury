package com.lct.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lct.bean.User;

import java.util.List;

/**
 * 在mybatis操作的时候，我们需要自己定义接口中实现的方法，并添加与之对应的UserMapper.xml文件，编写对应的sql语句
 * 在mybatis-plus操作的时候，只需要继承BaseMapper接口即可，对于通用的增删改查无需编写sql语句
 */
public interface UserMapper  {

    List<User> findAll();

//    int insertByAutoId(User user);

    //在插入的时候，mybatis-plus会根据输入的对象的字段的个数来动态的调整sql语句插入的字段
//    @Override
    int insert(User user);
}
