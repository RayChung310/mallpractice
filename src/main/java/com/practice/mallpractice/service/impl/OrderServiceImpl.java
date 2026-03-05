package com.practice.mallpractice.service.impl;

import com.practice.mallpractice.dao.OrderDao;
import com.practice.mallpractice.dao.ProductDao;
import com.practice.mallpractice.dao.UserDao;
import com.practice.mallpractice.dto.BuyItem;
import com.practice.mallpractice.dto.CreateOrderRequset;
import com.practice.mallpractice.model.Order;
import com.practice.mallpractice.model.OrderItem;
import com.practice.mallpractice.model.Product;
import com.practice.mallpractice.model.User;
import com.practice.mallpractice.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private UserDao userDao;

    @Override
    public Order getOrderById(Integer orderId) {
        Order order = orderDao.getOrderById(orderId);

        List<OrderItem> orderItemList = orderDao.getOrderItemsByOrderId(orderId);

        order.setOrderItemList(orderItemList);

        return order;
    }

    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequset createOrderRequset) {

        // 檢查user是否存在
        User user = userDao.getUserById(userId);

        if (user == null) {
            log.warn("該 userId {} 不存在", userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }


        // 計算訂單花費
        int totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();

        for (BuyItem buyItem : createOrderRequset.getBuyItemList()){
            Product product = productDao.getProductById(buyItem.getProductId());

            // 檢查 product 是否存在、stock 足夠
            if (product == null){
                log.warn("商品 {} 不存在", buyItem.getProductId());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            } else if (product.getStock() < buyItem.getQuantity()) {
                log.warn("商品 {} 庫存不夠，無法購買，剩餘庫存為 {}, 要購買的數量為 {}", buyItem.getProductId(), product.getStock(), buyItem.getQuantity());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }

            // 扣除商品庫存
            productDao.updateStock(product.getProductId(), product.getStock() - buyItem.getQuantity());

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
