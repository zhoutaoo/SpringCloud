package com.springboot.producer.dao;

import com.springboot.producer.entity.Product;
import com.springboot.producer.entity.param.ProductQueryParam;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProductMapper {

    @Select("select * from products where name=#{name}")
    List<Product> query(ProductQueryParam productQueryParam);

    @Insert("insert into products(name,description,updated_time,created_time,updated_by,created_by) values(#{name},#{description},now(),now(),#{updatedBy},#{createdBy})")
    int insert(Product product);

    @Delete("delete from products where id=#{id}")
    void delete(long id);

    @Update("update products set name=#{name},updated_time=now() where id=#{id}")
    void update(Product product);

    @Select("select id,name,description,updated_time,created_time,updated_by,created_by from products where id = #{id}")
    Product select(long id);

}