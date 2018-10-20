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
    Product get(long id);

    /**
     * 新增用户
     *
     * @param product
     * @return
     */
    long add(Product product);

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
    void update(Product product);

    /**
     * 根据id删除用户
     *
     * @param id
     */
    void delete(long id);
}
