package com.lct.study.service;

import com.lct.study.bean.User;

public interface UserService {

    public User getById(String id) throws Exception;

    public void insert() throws Exception;
}
