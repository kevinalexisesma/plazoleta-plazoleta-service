package com.plazoleta.plazoleta.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateDishRequest(
        @NotBlank String nombre,
        @Positive Double precio,
        @NotBlank String descripcion,
        @NotBlank String urlImagen,
        @NotBlank String categoria,
        @NotNull Long restaurantId,
        @NotNull Long ownerUserId) {
}
