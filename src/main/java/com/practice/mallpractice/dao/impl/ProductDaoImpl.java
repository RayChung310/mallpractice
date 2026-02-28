package com.practice.mallpractice.dao.impl;

import com.practice.mallpractice.dao.ProductDao;
import com.practice.mallpractice.model.Product;
import com.practice.mallpractice.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductDaoImpl implements ProductDao {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Product getProductById(Integer productId) {
        String sql = "SELECT product_id,product_name, category, image_url, " +
                "price, stock, description, created_date, " +
                "last_modified_date FROM product WHERE product_id = :productId; ";

        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);

        List<Product> query = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());

        // 至少存在一筆資料，從第一筆資料開始算
        if (query.size() > 0){
            return query.get(0);
        }else {
            return null;
        }
    }
}
