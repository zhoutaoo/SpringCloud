package com.springboot.cloud.demos.producer.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.cloud.demos.producer.dao.ProductMapper;
import com.springboot.cloud.demos.producer.entity.param.ProductQueryParam;
import com.springboot.cloud.demos.producer.entity.po.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RefreshScope
public class ProductService extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Value("${producer.product:123}")
    private String value;

    @Override
    public boolean add(Product product) {
        return this.save(product);
    }

    @Override
    @CacheEvict(value = "product", key = "#root.targetClass+'-'+#id")
    public boolean delete(String id) {
        return this.removeById(id);
    }

    @Override
    @CacheEvict(value = "product", key = "#root.targetClass+'-'+#product.id")
    public boolean update(Product product) {
        return this.updateById(product);
    }

    @Override
    @Cacheable(value = "product", key = "#root.targetClass+'-'+#id")
    public Product get(String id) {
        log.info("value:{}", value);
        return this.getById(id);
    }

    @Override
    public List<Product> query(ProductQueryParam productQueryParam) {
        QueryWrapper<Product> queryWrapper = productQueryParam.build();
        queryWrapper.eq("name", productQueryParam.getName());
        return this.list(queryWrapper);
    }
}
