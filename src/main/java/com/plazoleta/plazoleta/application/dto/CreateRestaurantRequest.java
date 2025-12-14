package com.plazoleta.plazoleta.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CreateRestaurantRequest(
        @NotBlank String nombre,
        @Pattern(regexp = "^\\d+$") String nit,
        @NotBlank String direccion,
        @Pattern(regexp = "^\\+?\\d{1,13}$") String telefono,
        @NotBlank String urlLogo,
        @NotNull Long ownerUserId) {
}
