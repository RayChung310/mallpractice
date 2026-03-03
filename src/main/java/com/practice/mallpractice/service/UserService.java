package com.practice.mallpractice.service;

import com.practice.mallpractice.dto.UserLoginRequest;
import com.practice.mallpractice.dto.UserRegisterRequest;
import com.practice.mallpractice.model.User;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;

public interface UserService {

    User getUserById(Integer userId);

    Integer register(UserRegisterRequest userRegisterRequest);

    User login(UserLoginRequest userLoginRequest);
}
