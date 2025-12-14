package com.plazoleta.plazoleta.application.mapper;

import com.plazoleta.plazoleta.application.dto.RestaurantResponse;
import com.plazoleta.plazoleta.domain.model.Restaurant;
import com.plazoleta.plazoleta.infrastructure.persistence.entity.RestaurantEntity;

public class RestaurantMapper {
    public static Restaurant toDomain(RestaurantEntity entity) {
        return new Restaurant(
                entity.getId(),
                entity.getNombre(),
                entity.getNit(),
                entity.getDireccion(),
                entity.getTelefono(),
                entity.getUrlLogo(),
                entity.getOwnerUserId());
    }

    public static RestaurantEntity toEntity(Restaurant domain) {
        RestaurantEntity entity = new RestaurantEntity();
        entity.setId(domain.getId());
        entity.setNombre(domain.getNombre());
        entity.setNit(domain.getNit());
        entity.setDireccion(domain.getDireccion());
        entity.setTelefono(domain.getTelefono());
        entity.setUrlLogo(domain.getUrlLogo());
        entity.setOwnerUserId(domain.getOwnerUserId());
        return entity;
    }

    public static RestaurantResponse toResponse(Restaurant domain) {
        return new RestaurantResponse(
                domain.getId(),
                domain.getNombre(),
                domain.getNit(),
                domain.getDireccion(),
                domain.getTelefono(),
                domain.getUrlLogo(),
                domain.getOwnerUserId());
    }
}
