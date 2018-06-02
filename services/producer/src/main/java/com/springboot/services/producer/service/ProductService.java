package com.springboot.services.producer.service;

import com.springboot.services.producer.dao.ProductMapper;
import com.springboot.services.producer.entity.param.ProductQueryParam;
import com.springboot.services.producer.entity.po.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductMapper productMapper;

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
        return productMapper.select(id);
    }

    @Override
    public List<Product> query(ProductQueryParam productQueryParam) {
        return productMapper.query(productQueryParam);
    }
}
