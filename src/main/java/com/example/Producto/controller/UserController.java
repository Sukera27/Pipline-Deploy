package com.example.Producto.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Producto.Service.UserService;
import com.example.Producto.controller.converter.Converter;
import com.example.Producto.controller.dto.UserDto;
import com.example.Producto.persistance.model.User;

import lombok.AllArgsConstructor;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    UserService userService;    

    Converter converter ;
    
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        ResponseEntity<List<UserDto>> response = ResponseEntity.ok().body( 
            userService.getAllUser()
                    .stream()              
                    .map(converter::toUserDto)
                    .toList()); 
        return response;
    }

}
