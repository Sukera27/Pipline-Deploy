package com.example.Producto.persistance.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data   
@NoArgsConstructor 
@Entity
@Table(name = "categorias")
public class Category {
    @Id
    @Column (name = "category_id")
    private Long categoryId;

    @Column (name = "category_name")
    private String categoryName;    
    
     @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
     
    private List<Product> products;
}
