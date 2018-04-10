package com.springboot.producer.service;

import com.springboot.producer.dao.ProductMapper;
import com.springboot.producer.entity.Product;
import com.springboot.producer.entity.param.ProductQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Product get(long id) {
        return productMapper.select(id);
    }

    @Override
    public long add(Product product) {
        return productMapper.insert(product);
    }

    @Override
    public List<Product> query(ProductQueryParam productQueryParam) {
        return productMapper.query(productQueryParam);
    }

    @Override
    public void update(Product product) {
        productMapper.update(product);
    }

    @Override
    public void delete(long id) {
        productMapper.delete(id);
    }
}
