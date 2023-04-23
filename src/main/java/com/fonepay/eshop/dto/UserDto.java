package com.fonepay.eshop.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserDto {
    private String name;
    private String email;
    private Set<String> roles = new HashSet<>();
}
