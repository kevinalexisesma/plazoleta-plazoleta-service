package com.plazoleta.plazoleta.domain.repository;

import com.plazoleta.plazoleta.domain.model.Dish;

public interface DishRepository {
    Dish save(Dish dish);
}
