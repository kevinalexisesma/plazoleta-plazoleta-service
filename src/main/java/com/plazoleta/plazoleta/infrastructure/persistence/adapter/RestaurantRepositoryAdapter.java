package com.plazoleta.plazoleta.infrastructure.persistence.adapter;

import com.plazoleta.plazoleta.domain.model.Restaurant;
import com.plazoleta.plazoleta.domain.repository.RestaurantRepository;
import com.plazoleta.plazoleta.application.mapper.RestaurantMapper;
import com.plazoleta.plazoleta.infrastructure.persistence.entity.RestaurantEntity;
import com.plazoleta.plazoleta.infrastructure.persistence.jpa.SpringDataRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestaurantRepositoryAdapter implements RestaurantRepository {
    private final SpringDataRestaurantRepository jpaRepo;

    @Override
    public Restaurant save(Restaurant restaurant) {
        RestaurantEntity entity = RestaurantMapper.toEntity(restaurant);
        RestaurantEntity saved = jpaRepo.save(entity);
        return RestaurantMapper.toDomain(saved);
    }
}
