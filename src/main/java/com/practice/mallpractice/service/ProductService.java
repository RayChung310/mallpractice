package com.practice.mallpractice.service;

import com.practice.mallpractice.dto.ProductRequest;
import com.practice.mallpractice.model.Product;

public interface ProductService {

    Product getProductById(Integer productId);


    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void  deleteProductById(Integer productUd);
}
