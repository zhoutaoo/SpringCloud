package com.springboot.cloud.gateway.admin.dao;

import com.springboot.cloud.gateway.admin.entity.param.GatewayRouteQueryParam;
import com.springboot.cloud.gateway.admin.entity.po.GatewayRoute;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GatewayRouteMapper {

    String PUBLIC_COLUMN = "id,uri,predicates,filters,status,description,updated_time,created_time,updated_by,created_by";

    @Options(useGeneratedKeys = true)
    @Insert("insert into gateway_routes(uri,predicates,filters,description,updated_time,created_time,updated_by,created_by)" +
            " values(#{uri},#{predicates},#{filters},#{description},now(),now(),#{updatedBy},#{createdBy})")
    long insert(GatewayRoute gatewayRoute);

    @Update("update gateway_routes set status='N' where id=#{id}")
    void delete(long id);

    @Update("update gateway_routes set uri=#{uri},predicates=#{predicates},filters=#{filters},description=#{description},status='Y',updated_by=#{updatedBy},updated_time=now()" +
            " where id=#{id}")
    void update(GatewayRoute gatewayRoute);

    @Select("select " + PUBLIC_COLUMN + " from gateway_routes where id=#{id} and status='Y'")
    GatewayRoute select(long id);

    @Select("<script>" +
            "select " + PUBLIC_COLUMN +
            " from gateway_routes" +
            " where status='Y'" +
            "<if test='uri!=null'>" +
            " and uri=#{uri}" +
            "</if>" +
            "<if test='createdTimeStart!=null'>" +
            " and <![CDATA[created_time>#{createdTimeStart}]]>" +
            "</if>" +
            "<if test='createdTimeEnd!=null'>" +
            " and <![CDATA[created_time<#{createdTimeEnd}]]>" +
            "</if>" +
            "</script>")
    List<GatewayRoute> query(GatewayRouteQueryParam gatewayRouteQueryParam);
}