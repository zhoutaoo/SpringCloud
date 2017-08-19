package com.springboot.oauth2.dao;

import com.springboot.oauth2.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    @Select("select * from users where username = #{username}")
    User loadByUsername(String username);
}