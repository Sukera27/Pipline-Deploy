package com.example.Producto.persistance.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor  
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false, unique = true)
    Long userId;

    @Column(name = "username", nullable = false, unique = true, length = 20)
    String username;
  
    @Column(name = "password", nullable = false)
    String password;

    @Column(name = "email", nullable = false, unique = true, length = 90)
    String email;


    @OneToOne(mappedBy = "owner", fetch = FetchType.LAZY)
    Dni documentDni;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="users_bought_productos", 
               joinColumns={@JoinColumn(name="Users_user_id", referencedColumnName = "user_id")}, 
               inverseJoinColumns={@JoinColumn(name="productos_producto_id", referencedColumnName = "producto_id")})
    List<Product> products;
}
