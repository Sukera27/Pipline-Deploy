package com.example.Producto.controller.converter;

import org.springframework.stereotype.Component;

import com.example.Producto.controller.dto.CategoryDto;
import com.example.Producto.controller.dto.ProductDto;
import com.example.Producto.controller.dto.UserDto;
import com.example.Producto.persistance.model.Category;
import com.example.Producto.persistance.model.Product;
import com.example.Producto.persistance.model.User;

@Component
public class Converter {
    
    public ProductDto toProductDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setImageUrl(product.getImageUrl());
         productDto.setCategory(toCategoryDto(product.getCategory()));
        return productDto;
    }

    public Product toProductEntity(ProductDto productDto){
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImageUrl());
        product.setCategory(toCategoryEntity(productDto.getCategory()));
        return product;
    }



    public UserDto toUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setEmail(user.getEmail());
        userDto.setProducts(user.getProducts().stream().map(this::toProductDto).toList());
        return userDto;
    }

    public User toUserEntity(UserDto userDto){
        User user = new User();
        user.setUserId(userDto.getUserId());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setProducts(userDto.getProducts().stream().map(this::toProductEntity).toList());
        return user;
    }

    public CategoryDto toCategoryDto(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(category.getCategoryId());
        categoryDto.setCategoryName(category.getCategoryName());
        return categoryDto;
    }

    public Category toCategoryEntity(CategoryDto categoryDto){
        Category category = new Category();
        category.setCategoryId(categoryDto.getCategoryId());
        category.setCategoryName(categoryDto.getCategoryName());
        return category;
    }

}
