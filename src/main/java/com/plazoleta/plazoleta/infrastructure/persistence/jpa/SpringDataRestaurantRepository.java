package com.plazoleta.plazoleta.infrastructure.persistence.jpa;

import com.plazoleta.plazoleta.infrastructure.persistence.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataRestaurantRepository extends JpaRepository<RestaurantEntity, Long> {
}
