package com.jackhance.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jackhance.springboot.mapper.UserPlusMapper;
import com.jackhance.springboot.model.User;
import com.jackhance.springboot.service.UserPlusService;
import org.springframework.stereotype.Service;

@Service
public class UserPlusServiceImpl extends ServiceImpl<UserPlusMapper, User> implements UserPlusService {
}
