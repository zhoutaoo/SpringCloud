package com.springboot.services.producer.jpa.dao;

import com.springboot.services.producer.jpa.entity.po.Product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ProductMapper extends PagingAndSortingRepository<Product, Long> {

}