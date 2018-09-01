package com.springboot.cloud.sysadmin.organization.dao;

import com.springboot.cloud.sysadmin.organization.entity.param.GroupQueryParam;
import com.springboot.cloud.sysadmin.organization.entity.po.Group;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GroupMapper {

    @Options(useGeneratedKeys = true)
    @Insert("insert into \"group\"(parent_id,name,description,updated_time,created_time,updated_by,created_by)" +
            " values(#{name},#{description},now(),now(),#{updatedBy},#{createdBy})")
    long insert(Group group);

    @Delete("delete from \"group\" where id=#{id}")
    void delete(long id);

    @Update("update \"group\" set name=#{name},description=#{description},updated_by=#{updatedBy},updated_time=now()" +
            " where id=#{id}")
    void update(Group group);

    @Select("select id,parent_id,name,description,updated_time,created_time,updated_by,created_by" +
            " from \"group\" where id=#{id}")
    Group select(long id);

    @Select("<script>" +
            "select id,parent_id,name,description,updated_time,created_time,updated_by,created_by" +
            " from \"group\"" +
            " where name=#{name}" +
            "<if test='createdTimeStart!=null'>" +
            " and <![CDATA[created_time>#{createdTimeStart}]]>" +
            "</if>" +
            "<if test='createdTimeEnd!=null'>" +
            " and <![CDATA[created_time<#{createdTimeEnd}]]>" +
            "</if>" +
            "</script>")
    List<Group> query(GroupQueryParam groupQueryParam);
}