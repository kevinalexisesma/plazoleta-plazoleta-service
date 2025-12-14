package com.plazoleta.plazoleta.infrastructure.persistence.adapter;

import com.plazoleta.plazoleta.domain.model.Dish;
import com.plazoleta.plazoleta.domain.repository.DishRepository;
import com.plazoleta.plazoleta.application.mapper.DishMapper;
import com.plazoleta.plazoleta.infrastructure.persistence.entity.DishEntity;
import com.plazoleta.plazoleta.infrastructure.persistence.jpa.SpringDataDishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DishRepositoryAdapter implements DishRepository {
    private final SpringDataDishRepository jpaRepo;

    @Override
    public Dish save(Dish dish) {
        DishEntity entity = DishMapper.toEntity(dish);
        DishEntity saved = jpaRepo.save(entity);
        return DishMapper.toDomain(saved);
    }
}
