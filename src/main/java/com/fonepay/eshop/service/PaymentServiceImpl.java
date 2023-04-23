package com.fonepay.eshop.service;

import com.fonepay.eshop.entity.Payment;
import com.fonepay.eshop.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    
    private final PaymentRepository paymentRepository;
    
    @Override
    public void save(Payment payment) {
        paymentRepository.save(payment);
    }
}
