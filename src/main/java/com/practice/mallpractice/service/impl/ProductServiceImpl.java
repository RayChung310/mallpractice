package com.practice.mallpractice.service.impl;

import com.practice.mallpractice.dao.ProductDao;
import com.practice.mallpractice.model.Product;
import com.practice.mallpractice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }
}
