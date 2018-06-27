package com.springboot.services.producer.dao;

import com.springboot.services.producer.entity.param.ProductQueryParam;
import com.springboot.services.producer.entity.po.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProductMapper {

    @Options(useGeneratedKeys = true)
    @Insert("insert into products(name,description,updated_time,created_time,updated_by,created_by)" +
            " values(#{name},#{description},now(),now(),#{updatedBy},#{createdBy})")
    long insert(Product product);

    @Delete("delete from products where id=#{id}")
    void delete(long id);

    @Update("update products set name=#{name},description=#{description},updated_by=#{updatedBy},updated_time=now()" +
            " where id=#{id}")
    void update(Product product);

    @Select("select id,name,description,updated_time,created_time,updated_by,created_by" +
            " from products where id=#{id}")
    Product select(long id);

    @Select("<script>" +
            "select id,name,description,updated_time,created_time,updated_by,created_by" +
            " from products" +
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