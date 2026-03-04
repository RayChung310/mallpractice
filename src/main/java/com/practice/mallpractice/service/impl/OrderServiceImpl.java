package com.practice.mallpractice.service.impl;

import com.practice.mallpractice.dao.OrderDao;
import com.practice.mallpractice.dao.ProductDao;
import com.practice.mallpractice.dto.BuyItem;
import com.practice.mallpractice.dto.CreateOrderRequset;
import com.practice.mallpractice.model.OrderItem;
import com.practice.mallpractice.model.Product;
import com.practice.mallpractice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ProductDao productDao;

    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequset createOrderRequset) {
        // 計算訂單花費
        int totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();

        for (BuyItem buyItem : createOrderRequset.getBuyItemList()){
            Product product = productDao.getProductById(buyItem.getProductId());

            int amount = buyItem.getQuantity() * product.getPrice();
            totalAmount = totalAmount + amount;

            // 轉換BuyItem to OrderItem
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(buyItem.getProductId());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(amount);

            orderItemList.add(orderItem);
        }


        // 創建訂單
        Integer orderId = orderDao.createOrder(userId, totalAmount);

        // 創建訂單內容
        orderDao.createOrderItems(orderId, orderItemList);

        return orderId;

    }
}
