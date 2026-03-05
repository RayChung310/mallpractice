package com.practice.mallpractice.service;

import com.practice.mallpractice.dto.CreateOrderRequset;
import com.practice.mallpractice.model.Order;

public interface OrderService {

    Order getOrderById(Integer orderId);

    Integer createOrder(Integer userId, CreateOrderRequset createOrderRequset);
}
