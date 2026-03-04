package com.practice.mallpractice.service;

import com.practice.mallpractice.dto.CreateOrderRequset;

public interface OrderService {

    Integer createOrder(Integer userId, CreateOrderRequset createOrderRequset);
}
