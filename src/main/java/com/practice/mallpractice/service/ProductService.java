package com.practice.mallpractice.service;

import com.practice.mallpractice.dto.ProductRequest;
import com.practice.mallpractice.model.Product;

public interface ProductService {

    Product getProductById(Integer productId);


    Integer createProduct(ProductRequest productRequest);
}
