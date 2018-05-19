package com.springboot.auth.authentication.dao;

import com.springboot.auth.authentication.entity.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Mapper
@Repository
public interface ResourceMapper {

    @Select("SELECT id,code,type,name,url,method,description,created_time,updated_time,created_by,updated_by" +
            " FROM resources")
    Set<Resource> findAll();

    @Select("<script>" +
            "SELECT DISTINCT rs.code,rs.url,rs.name,rs.type,rs.method,rs.description" +
            " FROM roles r" +
            " INNER JOIN roles_resources_relation rrr ON r.id = rrr.role_id" +
            " INNER JOIN resources rs ON rs.id = rrr.resource_id" +
            " WHERE r.code IN " +
            " <foreach collection='roleCodes' item='roleCode' index='index' open='(' close=')' separator=',' >" +
            "    #{roleCode}" +
            " </foreach>" +
            "</script>")
    Set<Resource> queryByRoleCodes(@Param("roleCodes") String[] roleCodes);
}