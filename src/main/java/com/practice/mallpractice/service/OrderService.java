package com.practice.mallpractice.service;

import com.practice.mallpractice.dto.CreateOrderRequset;
import com.practice.mallpractice.dto.OrderQueryParams;
import com.practice.mallpractice.model.Order;

import java.util.List;

public interface OrderService {

    Integer countOrder(OrderQueryParams orderQueryParams);

    List<Order> getOrders(OrderQueryParams orderQueryParams);

    Order getOrderById(Integer orderId);

    Integer createOrder(Integer userId, CreateOrderRequset createOrderRequset);
}
