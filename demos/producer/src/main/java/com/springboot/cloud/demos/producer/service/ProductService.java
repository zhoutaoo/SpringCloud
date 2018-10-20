package com.springboot.cloud.demos.producer.service;

import com.springboot.cloud.demos.producer.dao.ProductMapper;
import com.springboot.cloud.demos.producer.entity.param.ProductQueryParam;
import com.springboot.cloud.demos.producer.entity.po.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RefreshScope
public class ProductService implements IProductService {

    @Autowired
    private ProductMapper productMapper;

    @Value("${username}")
    private String value;

    @Override
    public long add(Product product) {
        return productMapper.insert(product);
    }

    @Override
    @CacheEvict(value = "product", key = "#root.targetClass+'-'+#id")
    public void delete(long id) {
        productMapper.delete(id);
    }

    @Override
    @CacheEvict(value = "product", key = "#root.targetClass+'-'+#product.id")
    public void update(Product product) {
        productMapper.update(product);
    }

    @Override
    @Cacheable(value = "product", key = "#root.targetClass+'-'+#id")
    public Product get(long id) {
        log.info("value:{}", value);
        return productMapper.select(id);
    }

    @Override
    public List<Product> query(ProductQueryParam productQueryParam) {
        return productMapper.query(productQueryParam);
    }
}
