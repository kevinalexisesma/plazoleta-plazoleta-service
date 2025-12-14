package com.plazoleta.plazoleta.infrastructure.web.controller;

import com.plazoleta.plazoleta.application.dto.CreateDishRequest;
import com.plazoleta.plazoleta.application.dto.DishResponse;
import com.plazoleta.plazoleta.application.dto.UpdateDishRequest;
import com.plazoleta.plazoleta.application.usecase.CreateDishUseCase;
import com.plazoleta.plazoleta.application.usecase.UpdateDishUseCase;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

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
    private final CreateDishUseCase createUseCase;
    private final UpdateDishUseCase updateUseCase;

    @PostMapping
    @Operation(summary = "Crear plato", description = "Crea un plato asociado a un restaurante")
    public ResponseEntity<DishResponse> create(@Valid @RequestBody CreateDishRequest req) {
        DishResponse resp = createUseCase.handle(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @PutMapping("/{dishId}")
    @Operation(summary = "Modificar plato", description = "Permite modificar precio y descripción de un plato")
    public ResponseEntity<DishResponse> update(
            @PathVariable Long dishId,
            @Valid @RequestBody UpdateDishRequest req) {
        UpdateDishRequest fixedReq = new UpdateDishRequest(dishId, req.precio(), req.descripcion(), req.ownerUserId());
        DishResponse resp = updateUseCase.handle(fixedReq);
        return ResponseEntity.ok(resp);
    }
}
