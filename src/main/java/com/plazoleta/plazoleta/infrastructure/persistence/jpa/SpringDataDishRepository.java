package com.plazoleta.plazoleta.infrastructure.persistence.jpa;

import com.plazoleta.plazoleta.infrastructure.persistence.entity.DishEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataDishRepository extends JpaRepository<DishEntity, Long> {
}
