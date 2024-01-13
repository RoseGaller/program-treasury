package com.lct.samples.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lct.samples.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
