package com.practice.mallpractice.service.impl;

import com.practice.mallpractice.dao.UserDao;
import com.practice.mallpractice.dto.UserLoginRequest;
import com.practice.mallpractice.dto.UserRegisterRequest;
import com.practice.mallpractice.model.User;
import com.practice.mallpractice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
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

        User user= userDao.getUserByEmail(userRegisterRequest.getEmail());

        // 註冊過email
        if (user != null){
            log.warn("該email {} 已經被註冊", userRegisterRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // MD5 生成雜湊值
        String hashPassWord = DigestUtils.md5DigestAsHex(userRegisterRequest.getPassword().getBytes());
        userRegisterRequest.setPassword(hashPassWord);

        // 創新的帳號
        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public User login(UserLoginRequest userLoginRequest) {
        User user = userDao.getUserByEmail(userLoginRequest.getEmail());

        // 檢查是否存在
        if (user == null) {
            log.warn("該email {} 尚未被註冊", userLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // 使用雜湊值
        String hashPassword = DigestUtils.md5DigestAsHex(userLoginRequest.getPassword().getBytes());

        // 比較密碼
        if (user.getPassword().equals(hashPassword)){
            return user;
        } else {
            log.warn("email {} 密碼不正確", userLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
