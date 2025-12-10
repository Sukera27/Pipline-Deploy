package com.example.Producto.Service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Producto.Service.UserService;
import com.example.Producto.persistance.model.User;
import com.example.Producto.persistance.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor 
public class UserServiceImpl implements UserService {
    
    UserRepository userRepository;  

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();      
    }

    

    
}
