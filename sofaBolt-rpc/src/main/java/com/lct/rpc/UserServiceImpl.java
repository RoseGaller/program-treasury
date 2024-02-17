package com.lct.rpc;

public class UserServiceImpl implements UserService{

    @Override
    public User getUser(Long id) {
        User user = new User();
        user.setName("alice");
        user.setUserId(id);
        return user;
    }
}
