package com.jackhance.springboot.mapper;

import com.jackhance.springboot.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    User selectUserById(Integer id);

    @Select("select * from user where id=#{id}")
    User selectUserById1(@Param("id") Integer id);
}
