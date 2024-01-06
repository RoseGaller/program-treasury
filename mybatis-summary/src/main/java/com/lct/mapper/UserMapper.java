package com.lct.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lct.bean.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    List<User> findAll();

    int insert(User user);
}
