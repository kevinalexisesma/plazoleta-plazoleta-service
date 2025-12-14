package com.plazoleta.plazoleta.domain.service;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DishDomainService {
    public void validarNombre(String nombre) {
        if (nombre.matches("^\\d+$")) {
            throw new IllegalArgumentException("El nombre no puede ser solo números");
        }
    }

    public void validarCategoria(String categoria) {
        List<String> permitidas = List.of("ENTRADA", "FUERTE", "POSTRE", "BEBIDA");
        if (!permitidas.contains(categoria.toUpperCase())) {
            throw new IllegalArgumentException("Categoría inválida");
        }
    }
}
