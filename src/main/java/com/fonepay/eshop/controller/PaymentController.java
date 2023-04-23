package com.fonepay.eshop.controller;

import com.fonepay.eshop.entity.Order;
import com.fonepay.eshop.entity.Payment;
import com.fonepay.eshop.security.UserDetailsImpl;
import com.fonepay.eshop.service.OrderService;
import com.fonepay.eshop.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/payment")
public class PaymentController {
    
    private final OrderService orderService;
    private final PaymentService paymentService;
    
    @PostMapping("/checkout")
    public ResponseEntity<String> checkout() {
        Long userId = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        List<Order> orderList = orderService.findUserOrders(userId);
        Payment payment = new Payment();
        orderList.forEach(order->{
            payment.setAmount(order.getTotalPrice());
            payment.setOrder(order);
            payment.setStatus("PAID");
            paymentService.save(payment);
            order.setPayment(payment);
        });
        
        return new ResponseEntity<>("Payment Complete", HttpStatus.OK);
    }
}
