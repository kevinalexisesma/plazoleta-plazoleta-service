package com.plazoleta.plazoleta.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "dishes")
@Getter
@Setter
public class DishEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(nullable = false)
    private Double precio;

    @Column(nullable = false, length = 500)
    private String descripcion;

    @Column(name = "url_imagen", nullable = false, length = 300)
    private String urlImagen;

    @Column(nullable = false, length = 20)
    private String categoria;

    @Column(name = "restaurant_id", nullable = false)
    private Long restaurantId;
}
