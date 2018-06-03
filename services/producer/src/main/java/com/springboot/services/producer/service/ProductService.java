package com.springboot.services.producer.service;

import com.springboot.services.producer.dao.ProductMapper;
import com.springboot.services.producer.entity.param.ProductQueryParam;
import com.springboot.services.producer.entity.po.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService implements IProductService {

    @Autowired
    private ProductMapper productMapper;

    //@Value("${username}")
    private String value;

    @Override
    public long add(Product product) {
        return productMapper.insert(product);
    }

    @Override
    public void delete(long id) {
        productMapper.delete(id);
    }

    @Override
    public void update(Product product) {
        productMapper.update(product);
    }

    @Override
    public Product get(long id) {
        log.info("value:{}", value);
        return productMapper.select(id);
    }

    @Override
    public List<Product> query(ProductQueryParam productQueryParam) {
        return productMapper.query(productQueryParam);
    }
}
