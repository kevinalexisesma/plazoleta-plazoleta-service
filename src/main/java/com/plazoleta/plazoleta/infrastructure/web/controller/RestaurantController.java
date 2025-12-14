package com.plazoleta.plazoleta.infrastructure.web.controller;

import com.plazoleta.plazoleta.application.dto.CreateRestaurantRequest;
import com.plazoleta.plazoleta.application.dto.RestaurantResponse;
import com.plazoleta.plazoleta.application.usecase.CreateRestaurantUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
@Tag(name = "Restaurants", description = "Gestión de restaurantes")
public class RestaurantController {
    private final CreateRestaurantUseCase useCase;

    @PostMapping
    @Operation(summary = "Crear restaurante", description = "Crea un restaurante asociado a un propietario")
    public ResponseEntity<RestaurantResponse> create(@Valid @RequestBody CreateRestaurantRequest req) {
        RestaurantResponse resp = useCase.handle(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }
}
