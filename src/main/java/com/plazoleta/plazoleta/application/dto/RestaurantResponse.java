package com.plazoleta.plazoleta.application.dto;

public record RestaurantResponse(
        Long id,
        String nombre,
        String nit,
        String direccion,
        String telefono,
        String urlLogo,
        Long ownerUserId) {
}
