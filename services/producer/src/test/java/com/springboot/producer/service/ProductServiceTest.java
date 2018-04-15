package com.springboot.producer.service;

import com.springboot.producer.entity.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {
    @Autowired
    private IProductService productService;

    @Test
    public void add() throws Exception {
        Product product = new Product("海报", "我是海报");
        product.setCreatedBy("system");
        product.setUpdatedBy("system");
        Assert.assertEquals(1, productService.add(product));
    }

}