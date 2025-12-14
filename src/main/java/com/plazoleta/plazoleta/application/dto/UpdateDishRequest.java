package com.plazoleta.plazoleta.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UpdateDishRequest(
        @NotNull Long dishId,
        @Positive Double precio,
        @NotBlank String descripcion,
        @NotNull Long ownerUserId) {
}
