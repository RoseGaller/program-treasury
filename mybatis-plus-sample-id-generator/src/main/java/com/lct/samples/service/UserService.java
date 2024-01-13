package com.lct.samples.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lct.samples.entity.User;
import com.lct.samples.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {

}