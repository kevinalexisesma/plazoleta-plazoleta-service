package com.plazoleta.plazoleta.domain.service;

import org.springframework.stereotype.Service;

@Service
public class RestaurantDomainService {
    public void validarNombre(String nombre) {
        if (nombre.matches("^\\d+$")) {
            throw new IllegalArgumentException("El nombre no puede ser solo números");
        }
    }
}
