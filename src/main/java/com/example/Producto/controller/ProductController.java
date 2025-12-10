package com.example.Producto.controller;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Producto.Service.ProductoService;
import com.example.Producto.controller.converter.Converter;
import com.example.Producto.controller.dto.ProductDto;
import com.example.Producto.persistance.model.Product;

import lombok.AllArgsConstructor;


@CrossOrigin    
@RestController  
@RequestMapping("/api/v1/products")  
@AllArgsConstructor
public class ProductController {

    ProductoService productoService;

    Converter converter;
    
    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        ResponseEntity<List<ProductDto>> response = 
        ResponseEntity.ok().body(productoService.getAllProducts().stream().map(converter::toProductDto).toList());  
        return response;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductsById(@PathVariable Long id) {
        ResponseEntity<ProductDto> response = ResponseEntity.ok(converter.toProductDto(productoService.getProductsById(id)));
        return response;
    }
    
    @PostMapping("")
    public ResponseEntity<ProductDto> saveProduct(@RequestBody ProductDto productDto) {
         Product product = converter.toProductEntity(productDto);
        Product saved = productoService.addProduct(product); 
        return ResponseEntity.ok(converter.toProductDto(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> editProduct( @RequestBody ProductDto productDto, @PathVariable Long id) {
        Product product = converter.toProductEntity(productDto);
        Product edited = productoService.editProduct(product,id);
        return ResponseEntity.ok(converter.toProductDto(edited));
    }

     @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable  Long id) {
        productoService.deleteProduct(id);
    }

    @PostMapping("/{productId}/buy/{userId}")
    public ResponseEntity<String> buyProduct(@PathVariable Long productId,@PathVariable Long userId) {
        productoService.buyProduct(userId, productId);
        return ResponseEntity.ok("Compra registrada exitosamente.");
    }
}
