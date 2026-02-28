package com.practice.mallpractice.dao;

import com.practice.mallpractice.dto.ProductRequest;
import com.practice.mallpractice.model.Product;

public interface ProductDao {

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);
}
