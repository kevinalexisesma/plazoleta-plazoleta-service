package com.plazoleta.plazoleta.application.dto;

public record DishResponse(
        Long id,
        String nombre,
        Double precio,
        String descripcion,
        String urlImagen,
        String categoria,
        Long restaurantId) {
}
