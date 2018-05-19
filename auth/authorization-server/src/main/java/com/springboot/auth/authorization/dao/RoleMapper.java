package com.springboot.auth.authorization.dao;

import com.springboot.auth.authorization.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Mapper
@Repository
public interface RoleMapper {

    @Select("SELECT DISTINCT r.code,r.name,r.description" +
            " FROM  users_roles_relation urr" +
            " INNER JOIN roles r ON r.id = urr.role_id" +
            " WHERE urr.user_id = #{userId}")
    Set<Role> queryByUserId(long userId);
}