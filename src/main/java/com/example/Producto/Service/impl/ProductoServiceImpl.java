package com.example.Producto.Service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Producto.Service.ProductoService;
import com.example.Producto.persistance.model.Product;
import com.example.Producto.persistance.model.User;
import com.example.Producto.persistance.repository.ProductoRepository;
import com.example.Producto.persistance.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ProductoServiceImpl implements ProductoService  {

    ProductoRepository productoRepository;

    UserRepository userRepository;
    
    @Override
    public List<Product> getAllProducts() {
        return productoRepository.findAll();
    }
    @Override
    public Product getProductsById(Long id) {
        return productoRepository.findById(id).
        orElseThrow(() -> new RuntimeException("Product not found"));
    }
    @Override
    public Product addProduct(Product product) {
       return productoRepository.save(product);
    }
    @Override
    public Product editProduct(Product product, Long id) {
        Product productoEditado = productoRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        productoEditado.setName(product.getName()); 
        productoEditado.setDescription(product.getDescription());   
        productoEditado.setPrice(product.getPrice()); 
        productoEditado.setImageUrl(product.getImageUrl());
        return productoRepository.save(productoEditado);
    }

    @Override
    public void deleteProduct(Long id) {
         productoRepository.deleteById(id);       
    }

    @Override
    public void buyProduct(Long userId, Long productId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productoRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        user.getProducts().add(product);
        userRepository.save(user);
    }
}
