package com.practice.mallpractice.dao;

import com.practice.mallpractice.dto.UserRegisterRequest;
import com.practice.mallpractice.model.User;

public interface UserDao {

    User getUserById(Integer userId);

    Integer createUser(UserRegisterRequest userRegisterRequest);
}
