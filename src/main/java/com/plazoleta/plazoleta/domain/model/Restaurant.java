package com.plazoleta.plazoleta.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Restaurant {
    private Long id;
    private String nombre;
    private String nit;
    private String direccion;
    private String telefono;
    private String urlLogo;
    private Long ownerUserId;
}
