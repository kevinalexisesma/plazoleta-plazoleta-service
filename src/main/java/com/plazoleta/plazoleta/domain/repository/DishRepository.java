package com.plazoleta.plazoleta.domain.repository;

import com.plazoleta.plazoleta.domain.model.Dish;
import java.util.Optional;

public interface DishRepository {
    Dish save(Dish dish);

    Optional<Dish> findById(Long id);
}
