package com.plazoleta.plazoleta.application.mapper;

import com.plazoleta.plazoleta.application.dto.DishResponse;
import com.plazoleta.plazoleta.domain.model.Dish;
import com.plazoleta.plazoleta.infrastructure.persistence.entity.DishEntity;

public class DishMapper {
    public static Dish toDomain(DishEntity entity) {
        return new Dish(
                entity.getId(),
                entity.getNombre(),
                entity.getPrecio(),
                entity.getDescripcion(),
                entity.getUrlImagen(),
                entity.getCategoria(),
                entity.getRestaurantId());
    }

    public static DishEntity toEntity(Dish domain) {
        DishEntity entity = new DishEntity();
        entity.setId(domain.getId());
        entity.setNombre(domain.getNombre());
        entity.setPrecio(domain.getPrecio());
        entity.setDescripcion(domain.getDescripcion());
        entity.setUrlImagen(domain.getUrlImagen());
        entity.setCategoria(domain.getCategoria());
        entity.setRestaurantId(domain.getRestaurantId());
        return entity;
    }

    public static DishResponse toResponse(Dish domain) {
        return new DishResponse(
                domain.getId(),
                domain.getNombre(),
                domain.getPrecio(),
                domain.getDescripcion(),
                domain.getUrlImagen(),
                domain.getCategoria(),
                domain.getRestaurantId());
    }
}
