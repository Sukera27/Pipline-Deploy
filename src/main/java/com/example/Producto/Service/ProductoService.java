package com.example.Producto.Service;

import java.util.List;

import com.example.Producto.persistance.model.Product;

public interface ProductoService {
        public List<Product> getAllProducts();
        public Product getProductsById(Long id);
        public Product addProduct(Product product);
        public Product editProduct(Product product, Long id);
        public void deleteProduct( Long id);
        public void  buyProduct(Long userId,  Long productId);
}
 