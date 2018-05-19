package com.springboot.oauth2.dao;

import com.springboot.oauth2.entity.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Mapper
@Repository
public interface ResourceMapper {

    @Select("SELECT id,code,type,name,url,method,description,created_time,updated_time,created_by,updated_by" +
            " FROM resources")
    Set<Resource> findAll();

    @Select("SELECT rs.id,rs.code,rs.name,rs.type,rs.method,rs.description" +
            " FROM roles r" +
            " INNER JOIN roles_resources_relation rrr ON r.id = rrr.role_id" +
            " INNER JOIN resources rs ON rs.id = rrr.resource_id")
    Set<Resource> queryByRoleCodes(String[] roleCodes);
}