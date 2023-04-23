package com.fonepay.eshop;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootTest
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
class EshopApplicationTests {
    
    @Test
    void contextLoads() {
    }
    
}
