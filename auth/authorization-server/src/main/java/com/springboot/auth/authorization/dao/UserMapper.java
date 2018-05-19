package com.springboot.auth.authorization.dao;

import com.springboot.auth.authorization.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    @Select("SELECT id,username,password,enabled,account_non_expired,credentials_non_expired,account_non_locked,name,mobile,created_time,updated_time,created_by,updated_by" +
            " FROM users WHERE username = #{username}")
    User getByUsername(String username);
}