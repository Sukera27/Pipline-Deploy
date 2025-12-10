package com.example.Producto.controller.dto;

import java.util.List;

import lombok.Data;

@Data
public class ProductDto {
    
    Long id;  
     String name;
     String description;
     Double price;
    String imageUrl;
    CategoryDto category; 
}
