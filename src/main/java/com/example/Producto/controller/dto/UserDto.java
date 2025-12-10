package com.example.Producto.controller.dto;

import java.util.List;

import lombok.Data;

@Data
public class UserDto {
    
    Long userId;
    String username;
    String password;
    String email;
    DniDto   documentDni;
    List<ProductDto> products;
}
