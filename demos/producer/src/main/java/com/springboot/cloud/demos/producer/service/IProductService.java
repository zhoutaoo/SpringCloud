package com.springboot.cloud.demos.producer.service;

import com.springboot.cloud.demos.producer.entity.param.ProductQueryParam;
import com.springboot.cloud.demos.producer.entity.po.Product;

import java.util.List;

public interface IProductService {
    /**
     * 获取用户
     *
     * @param id
     * @return
     */
    Product get(String id);

    /**
     * 新增用户
     *
     * @param product
     * @return
     */
    boolean add(Product product);

    /**
     * 查询用户
     *
     * @return
     */
    List<Product> query(ProductQueryParam productQueryParam);

    /**
     * 更新用户信息
     *
     * @param product
     */
    boolean update(Product product);

    /**
     * 根据id删除用户
     *
     * @param id
     */
    boolean delete(String id);
}
