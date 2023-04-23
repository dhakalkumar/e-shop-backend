package com.fonepay.eshop.service;

import com.fonepay.eshop.entity.Product;

import java.util.List;

public interface ProductService {
    
    List<Product> findAll();
    
    Product findById(Long id);
}
