package com.fonepay.eshop.service;

import com.fonepay.eshop.entity.Order;
import com.fonepay.eshop.entity.User;

import java.util.List;

public interface OrderService {
    void save(Order order);
    
    List<Order> findUserOrders(Long userId);
    
}
