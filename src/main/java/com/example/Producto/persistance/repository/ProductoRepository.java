package com.example.Producto.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Producto.persistance.model.Product;

@Repository
public interface ProductoRepository extends JpaRepository<Product, Object> {

}
