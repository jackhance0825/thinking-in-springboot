package com.jackhance.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jackhance.springboot.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserPlusMapper extends BaseMapper<User> {
}
