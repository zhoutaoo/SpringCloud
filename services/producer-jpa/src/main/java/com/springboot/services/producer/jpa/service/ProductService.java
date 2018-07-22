package com.springboot.services.producer.jpa.service;

import com.google.common.collect.Lists;
import com.springboot.services.producer.jpa.dao.ProductMapper;
import com.springboot.services.producer.jpa.entity.param.ProductQueryParam;
import com.springboot.services.producer.jpa.entity.po.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService implements IProductService {

    @Autowired
    private ProductMapper productMapper;

    @Value("${spring.boot.admin.url}")
    private String value;

    @Override
    public long add(Product product) {
        return productMapper.save(product).getId();
    }

    @Override
    public void delete(long id) {
        productMapper.deleteById(id);
    }

    @Override
    public void update(Product product) {
        productMapper.save(product);
    }

    @Override
    public Product get(long id) {
        log.info("value:{}", value);
        return productMapper.findById(id).orElse(new Product());
    }

    @Override
    public List<Product> query(ProductQueryParam productQueryParam) {
        return Lists.newArrayList(productMapper.findAll());
    }
}
