package com.jackhance.springboot.service;

import com.jackhance.springboot.model.User;

public interface UserService {

    User selectUserById(Integer id);

    User selectUserById1(Integer id);
}
