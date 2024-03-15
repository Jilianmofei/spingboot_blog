package com.yoyo.service.impl;

import com.yoyo.mapper.UserMapper;
import com.yoyo.pojo.User;
import com.yoyo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    UserMapper userMapper;
    @Override
    public User findUserByCondition(Map<String, Object> map) {
        return userMapper.findUserByCondition(map);
    }

}
