package com.practice.mallpractice.controller;

import com.practice.mallpractice.dto.CreateOrderRequset;
import com.practice.mallpractice.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    // 先有帳號，再創建訂單
    @PostMapping("/users/{userId}/orders")
    public ResponseEntity<?> createOrder(@PathVariable Integer userId,
                                         @RequestBody @Valid CreateOrderRequset createOrderRequset){

        Integer orderId = orderService.createOrder(userId, createOrderRequset);

        return ResponseEntity.status(HttpStatus.CREATED).body(orderId);
    }
}
