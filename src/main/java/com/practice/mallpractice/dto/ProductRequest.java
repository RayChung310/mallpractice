package com.practice.mallpractice.dto;

import com.practice.mallpractice.constant.ProductCategory;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {

    // 前端要傳的參數
    @NotNull
    private String productName;

    @NotNull
    private ProductCategory category;

    @NotNull
    private String imageUrl;

    @NotNull
    private Integer price;

    @NotNull
    private Integer stock;

    private String description;
}
