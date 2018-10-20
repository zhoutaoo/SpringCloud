package com.springboot.cloud.demos.producer.dao;

import com.springboot.cloud.demos.producer.entity.param.ProductQueryParam;
import com.springboot.cloud.demos.producer.entity.po.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProductMapper {

    @Options(useGeneratedKeys = true)
    @Insert("insert into product(name,description,updated_time,created_time,updated_by,created_by)" +
            " values(#{name},#{description},now(),now(),#{updatedBy},#{createdBy})")
    long insert(Product product);

    @Delete("delete from product where id=#{id}")
    void delete(long id);

    @Update("update product set name=#{name},description=#{description},updated_by=#{updatedBy},updated_time=now()" +
            " where id=#{id}")
    void update(Product product);

    @Select("select id,name,description,updated_time,created_time,updated_by,created_by" +
            " from product where id=#{id}")
    Product select(long id);

    @Select("<script>" +
            "select id,name,description,updated_time,created_time,updated_by,created_by" +
            " from product" +
            " where name=#{name}" +
            "<if test='createdTimeStart!=null'>" +
            " and <![CDATA[created_time>#{createdTimeStart}]]>" +
            "</if>" +
            "<if test='createdTimeEnd!=null'>" +
            " and <![CDATA[created_time<#{createdTimeEnd}]]>" +
            "</if>" +
            "</script>")
    List<Product> query(ProductQueryParam productQueryParam);
}