package com.practice.mallpractice.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class CreateOrderRequset {

    @Getter
    @Setter
    @NotEmpty
    private List<BuyItem> buyItemList;
}
