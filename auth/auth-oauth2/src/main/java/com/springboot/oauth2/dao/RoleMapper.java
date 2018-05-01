package com.springboot.oauth2.dao;

import com.springboot.oauth2.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Mapper
@Repository
public interface RoleMapper {

    @Select("SELECT DISTINCT r.code,r.name,r.descrition FROM roles r LEFT JOIN users_roles_relation urr ON r.id = urr.role_id where urr.user_id = #{userId}")
    Set<Role> findByUserId(long userId);
}