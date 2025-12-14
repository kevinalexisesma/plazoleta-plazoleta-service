package com.plazoleta.plazoleta.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Dish {
    private Long id;
    private String nombre;
    private Double precio;
    private String descripcion;
    private String urlImagen;
    private String categoria;
    private Long restaurantId;
}
