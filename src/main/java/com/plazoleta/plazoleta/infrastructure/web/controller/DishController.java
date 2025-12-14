package com.plazoleta.plazoleta.infrastructure.web.controller;

import com.plazoleta.plazoleta.application.dto.CreateDishRequest;
import com.plazoleta.plazoleta.application.dto.DishResponse;
import com.plazoleta.plazoleta.application.usecase.CreateDishUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/dishes")
@RequiredArgsConstructor
@Tag(name = "Dishes", description = "Gestión de platos")
public class DishController {
    private final CreateDishUseCase useCase;

    @PostMapping
    @Operation(summary = "Crear plato", description = "Crea un plato asociado a un restaurante")
    public ResponseEntity<DishResponse> create(@Valid @RequestBody CreateDishRequest req) {
        DishResponse resp = useCase.handle(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }
}
