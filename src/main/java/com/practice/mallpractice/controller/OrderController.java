package com.practice.mallpractice.controller;

import com.practice.mallpractice.dto.CreateOrderRequset;
import com.practice.mallpractice.dto.OrderQueryParams;
import com.practice.mallpractice.model.Order;
import com.practice.mallpractice.service.OrderService;
import com.practice.mallpractice.util.Page;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/users/{userId}/orders")
    public ResponseEntity<Page<Order>> getOrders(
            @PathVariable Integer userId,
            @RequestParam(defaultValue = "10") @Max(1000) @Min(0) Integer limit,
            @RequestParam(defaultValue = "0") @Min(0) Integer offset
    ){
        OrderQueryParams orderQueryParams = new OrderQueryParams();
        orderQueryParams.setUserId(userId);
        orderQueryParams.setLimit(limit);
        orderQueryParams.setOffset(offset);

        // 取得 order list
        List<Order> orderList = orderService.getOrders(orderQueryParams);

        // 取得 order 總數量
        Integer count = orderService.countOrder(orderQueryParams);

        // 分頁
        Page<Order> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(count);
        page.setResults(orderList);


        return ResponseEntity.status(HttpStatus.OK).body(page);


    }


    // 先有帳號，再創建訂單
    @PostMapping("/users/{userId}/orders")
    public ResponseEntity<?> createOrder(@PathVariable Integer userId,
                                         @RequestBody @Valid CreateOrderRequset createOrderRequset){

        Integer orderId = orderService.createOrder(userId, createOrderRequset);

        Order order = orderService.getOrderById(orderId);

        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }
}
