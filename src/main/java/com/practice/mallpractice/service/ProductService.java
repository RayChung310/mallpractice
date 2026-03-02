package com.practice.mallpractice.service;

import com.practice.mallpractice.dto.ProductQueryParams;
import com.practice.mallpractice.dto.ProductRequest;
import com.practice.mallpractice.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts(ProductQueryParams productQueryParams);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void  deleteProductById(Integer productUd);
}
