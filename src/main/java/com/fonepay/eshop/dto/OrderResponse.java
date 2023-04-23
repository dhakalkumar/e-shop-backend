package com.fonepay.eshop.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderResponse {
    private String name;
    private String productName;
    private int quantity;
    private BigDecimal price;
}
