package com.example.Producto.persistance.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "dnis")
public class Dni {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dni_id", nullable = false, unique = true)
    Integer dniId;

    @Column(name = "number", nullable = false, unique = true, length = 9)
    String number;

    @Column(name = "front_img")
    String frontImg;

    @Column(name = "back_img")
    String backImg;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="Users_user_id", referencedColumnName = "user_id")
    @JsonIgnore
    User owner;
}
