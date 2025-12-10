package com.example.Producto.persistance.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor  
@Entity
@Table(name = "productos")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "producto_id")    
    private  Long id;  
    @Column (name = "product_name" ) 
    private String name;
    @Column (name = "description" ) 
    private String description;
    @Column (name = "price" ) 
    private Double price;
    @Column (name = "image_url") 
    private String imageUrl;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    Category category; 
    
    @ManyToMany(mappedBy="products", fetch = FetchType.LAZY)
    List<User> usersWhobought;
    

}
