package com.springboot.cloud.gateway.admin.dao;

import com.springboot.cloud.gateway.admin.entity.param.GatewayRouteQueryParam;
import com.springboot.cloud.gateway.admin.entity.po.GatewayRoute;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GatewayRouteMapper {

    String PUBLIC_COLUMN = "id,parent_id,name,description,updated_time,created_time,updated_by,created_by";

    @Options(useGeneratedKeys = true)
    @Insert("insert into \"gateway_route\"(parent_id,name,description,updated_time,created_time,updated_by,created_by)" +
            " values(#{name},#{description},now(),now(),#{updatedBy},#{createdBy})")
    long insert(GatewayRoute gatewayRoute);

    @Delete("delete from \"gateway_route\" where id=#{id}")
    void delete(long id);

    @Update("update \"gateway_route\" set name=#{name},description=#{description},updated_by=#{updatedBy},updated_time=now()" +
            " where id=#{id}")
    void update(GatewayRoute gatewayRoute);

    @Select("select " + PUBLIC_COLUMN + " from \"gateway_route\" where id=#{id}")
    GatewayRoute select(long id);

    @Select("select " + PUBLIC_COLUMN + " from \"gateway_route\" where parent_id=#{id}")
    List<GatewayRoute> queryByParentId(long id);

    @Select("<script>" +
            "select " + PUBLIC_COLUMN +
            " from \"gateway_route\"" +
            " where name=#{name}" +
            "<if test='createdTimeStart!=null'>" +
            " and <![CDATA[created_time>#{createdTimeStart}]]>" +
            "</if>" +
            "<if test='createdTimeEnd!=null'>" +
            " and <![CDATA[created_time<#{createdTimeEnd}]]>" +
            "</if>" +
            "</script>")
    List<GatewayRoute> query(GatewayRouteQueryParam gatewayRouteQueryParam);
}