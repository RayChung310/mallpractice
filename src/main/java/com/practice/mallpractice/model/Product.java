package com.practice.mallpractice.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private Integer productId;
    private String productName;
    private String cateory;
    private String imageUrl;
    private Integer price;
    private Integer stock;
    private String description;
    private Date createDate;
    private Date lastModifiedDate;
}
