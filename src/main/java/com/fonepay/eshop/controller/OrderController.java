package com.fonepay.eshop.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fonepay.eshop.dto.OrderRequest;
import com.fonepay.eshop.dto.OrderResponse;
import com.fonepay.eshop.entity.Order;
import com.fonepay.eshop.entity.Product;
import com.fonepay.eshop.entity.User;
import com.fonepay.eshop.repository.OrderRepository;
import com.fonepay.eshop.security.UserDetailsImpl;
import com.fonepay.eshop.security.UserDetailsServiceImpl;
import com.fonepay.eshop.security.jwt.AuthTokenFilter;
import com.fonepay.eshop.security.jwt.JwtUtils;
import com.fonepay.eshop.service.OrderService;
import com.fonepay.eshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
@RestController
@Slf4j
@RequiredArgsConstructor
public class OrderController {
    
    private final OrderService orderService;
    private final UserDetailsServiceImpl userService;
    private final ProductService productService;
    
    @PostMapping("/order")
    public ResponseEntity<String> makeOrder(@RequestBody OrderRequest orderRequest) {
        Product product = productService.findById(orderRequest.getProductId());
        User user = userService.findById(orderRequest.getUserId());
        int quantity = orderRequest.getQuantity();
        BigDecimal price = product.getPrice();
        BigDecimal totalPrice = price.multiply(new BigDecimal(quantity));
        
        Order order = new Order();
        order.setProduct(product);
        order.setUser(user);
        order.setQuantity(quantity);
        order.setTotalPrice(totalPrice);
        orderService.save(order);
        
        return new ResponseEntity<>("Your order has been placed", HttpStatus.OK);
    }
    
    @GetMapping("/orders/get")
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        Long userId = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        List<Order> orderList = orderService.findUserOrders(userId);
        List<OrderResponse> orderResponseList = orderList.stream().map(order->{
            OrderResponse orderResponse = new OrderResponse();
            orderResponse.setName(order.getUser().getName());
            orderResponse.setProductName(order.getProduct().getProductName());
            orderResponse.setQuantity(order.getQuantity());
            orderResponse.setPrice(order.getTotalPrice());
            return orderResponse;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(orderResponseList, HttpStatus.OK);
    }
    
}
