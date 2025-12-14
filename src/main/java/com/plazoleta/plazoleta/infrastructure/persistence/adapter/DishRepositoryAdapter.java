package com.plazoleta.plazoleta.infrastructure.persistence.adapter;

import com.plazoleta.plazoleta.domain.model.Dish;
import com.plazoleta.plazoleta.domain.repository.DishRepository;
import com.plazoleta.plazoleta.application.mapper.DishMapper;
import com.plazoleta.plazoleta.infrastructure.persistence.entity.DishEntity;
import com.plazoleta.plazoleta.infrastructure.persistence.jpa.SpringDataDishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Dish> findById(Long id) {
        return jpaRepo.findById(id).map(DishMapper::toDomain);
    }
}
