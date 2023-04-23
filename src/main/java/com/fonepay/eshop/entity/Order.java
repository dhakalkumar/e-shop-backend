package com.fonepay.eshop.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "orders")
@Data
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    
    @Column(name = "quantity")
    private int quantity;
    
    @Column(name = "total_price")
    private BigDecimal totalPrice;
    
    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;
}
