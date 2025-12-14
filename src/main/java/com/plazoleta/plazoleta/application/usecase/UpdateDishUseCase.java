package com.plazoleta.plazoleta.application.usecase;

import com.plazoleta.plazoleta.application.dto.UpdateDishRequest;
import com.plazoleta.plazoleta.application.dto.DishResponse;
import com.plazoleta.plazoleta.application.mapper.DishMapper;
import com.plazoleta.plazoleta.domain.model.Dish;
import com.plazoleta.plazoleta.domain.repository.DishRepository;
import com.plazoleta.plazoleta.infrastructure.client.UsersClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateDishUseCase {
    private final DishRepository repo;
    private final UsersClient usersClient;

    @Transactional
    public DishResponse handle(UpdateDishRequest req) {
        if (!usersClient.esOwner(req.ownerUserId())) {
            throw new IllegalArgumentException("El usuario no es propietario válido");
        }

        Dish dish = repo.findById(req.dishId())
                .orElseThrow(() -> new IllegalArgumentException("Plato no encontrado"));

        dish.setPrecio(req.precio());
        dish.setDescripcion(req.descripcion());

        Dish updated = repo.save(dish);
        return DishMapper.toResponse(updated);
    }
}
