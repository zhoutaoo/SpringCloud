package com.springboot.oauth2.dao;

import com.springboot.oauth2.entity.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Mapper
@Repository
public interface ResourceMapper {

    @Select("SELECT id,code,type,name,url,method,descrition,created_time,updated_time,created_by,updated_by FROM resources")
    Set<Resource> findAll();


    @Select("SELECT DISTINCT rs.code,rs.url,rs.method FROM  users_roles_relation urr LEFT JOIN roles_resources_relation rrr ON urr.role_id = rrr.role_id LEFT JOIN resources rs ON rs.id = rrr.resource_id where urr.user_id = #{userId}")
    Set<Resource> findByUserId(long userId);
}