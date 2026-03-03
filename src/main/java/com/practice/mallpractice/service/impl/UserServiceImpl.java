package com.practice.mallpractice.service.impl;

import com.practice.mallpractice.dao.UserDao;
import com.practice.mallpractice.dto.UserRegisterRequest;
import com.practice.mallpractice.model.User;
import com.practice.mallpractice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        return userDao.createUser(userRegisterRequest);
    }
}
