package com.springboot.producer.dao;

import com.springboot.producer.entity.User;
import com.springboot.producer.entity.param.UserQueryParam;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    @Select("select * from users where name=#{name}")
    List<User> query(UserQueryParam userQueryParam);

    @Insert("insert into users(name, updatedDate) values(#{name}, #{updatedDate})")
    int insert(User user);

    @Delete("delete from users where id=#{id}")
    void delete(long id);

    @Update("update users set name=#{name},updatedDate=#{updatedDate} where id=#{id}")
    void update(User user);

    @Select("select * from users where id = #{id}")
    User select(long id);

}