package com.jackhance.springboot.service.impl;

import com.jackhance.springboot.mapper.UserMapper;
import com.jackhance.springboot.model.User;
import com.jackhance.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper mapper;

    @Override
    public User selectUserById(Integer id) {
        return mapper.selectUserById(id);
    }

    @Override
    public User selectUserById1(Integer id) {
        return mapper.selectUserById1(id);
    }
}
