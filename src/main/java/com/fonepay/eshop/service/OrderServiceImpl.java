package com.fonepay.eshop.service;

import com.fonepay.eshop.entity.Order;
import com.fonepay.eshop.entity.User;
import com.fonepay.eshop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    
    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }
    
    @Override
    public List<Order> findUserOrders(Long userId) {
        return orderRepository.findByUserId(userId);
    }
}
