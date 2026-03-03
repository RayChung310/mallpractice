package com.practice.mallpractice.service;

import com.practice.mallpractice.dto.UserRegisterRequest;
import com.practice.mallpractice.model.User;

public interface UserService {

    User getUserById(Integer userId);

    Integer register(UserRegisterRequest userRegisterRequest);

}
