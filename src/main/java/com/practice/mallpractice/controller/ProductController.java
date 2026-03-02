package com.practice.mallpractice.controller;

import com.practice.mallpractice.constant.ProductCategory;
import com.practice.mallpractice.dto.ProductQueryParams;
import com.practice.mallpractice.dto.ProductRequest;
import com.practice.mallpractice.model.Product;
import com.practice.mallpractice.service.ProductService;
import com.practice.mallpractice.util.Page;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
public class ProductController {



    @Autowired
    private ProductService productService;

    // 查詢商品列表
    @GetMapping("/products")
    public ResponseEntity<Page<Product>> getProducts(
            // 查詢條件 Filter
            @RequestParam(required = false) ProductCategory category,
            @RequestParam(required = false) String search,
            // 排序 Sort
            @RequestParam(defaultValue = "created_date") String orderBy,
            @RequestParam(defaultValue = "desc") String sort,
            // 分頁 pagination
            @RequestParam(defaultValue = "5") @Max(1000) @Min(0) Integer limit,
            @RequestParam(defaultValue = "0") @Min(0) Integer offset
    ){
        ProductQueryParams productQueryParams = new ProductQueryParams();
        productQueryParams.setCategory(category);
        productQueryParams.setSearch(search);
        productQueryParams.setOrderBy(orderBy);
        productQueryParams.setSort(sort);
        productQueryParams.setLimit(limit);
        productQueryParams.setOffset(offset);

        // 去得 productList
        List<Product> productList = productService.getProducts(productQueryParams);

        // 取得總筆數
        Integer total = productService.countProduct(productQueryParams);

        // 分頁
        Page<Product> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(total);
        page.setResults(productList);

        return ResponseEntity.status(HttpStatus.OK).body(page);
    }


    // 查詢商品
    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(
            @PathVariable Integer productId) {
        Product product = productService.getProductById(productId);
        if (product != null) {
            return ResponseEntity.status(HttpStatus.OK).body(product);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // 新增商品
    @PostMapping("/products")
    public ResponseEntity<Product> postProduct(@RequestBody @Valid ProductRequest productRequest) {
        Integer productId = productService.createProduct(productRequest);

        Product product = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    // 修改商品
    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProductID(@PathVariable Integer productId,
                                                   @RequestBody @Valid ProductRequest productRequest) {
        // 檢查product是否存在
        Product product = productService.getProductById(productId);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        productService.updateProduct(productId, productRequest);

        Product updateProduct = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.OK).body(updateProduct);


    }


    // 刪除商品
    @DeleteMapping("/products/{productId}")
    public ResponseEntity<?> deleteProductId(@PathVariable Integer productId) {
        productService.deleteProductById(productId);

        // 確定結果，商品消失
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}