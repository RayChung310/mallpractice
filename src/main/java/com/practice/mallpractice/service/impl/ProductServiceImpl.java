package com.practice.mallpractice.service.impl;

import com.practice.mallpractice.constant.ProductCategory;
import com.practice.mallpractice.dao.ProductDao;
import com.practice.mallpractice.dto.ProductQueryParams;
import com.practice.mallpractice.dto.ProductRequest;
import com.practice.mallpractice.model.Product;
import com.practice.mallpractice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> getProducts(ProductQueryParams productQueryParams) {
        return productDao.getProducts(productQueryParams);
    }

    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        return productDao.createProduct(productRequest);
    }

    @Override
    public void updateProduct (Integer productId, ProductRequest productRequest) {
        productDao.updateById(productId, productRequest);
    }

    @Override
    public void deleteProductById(Integer productId) {
        productDao.deleteProductById(productId);
    }
}
