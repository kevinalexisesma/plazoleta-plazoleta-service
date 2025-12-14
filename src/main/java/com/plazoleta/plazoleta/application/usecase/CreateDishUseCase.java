package com.plazoleta.plazoleta.application.usecase;

import com.plazoleta.plazoleta.application.dto.CreateDishRequest;
import com.plazoleta.plazoleta.application.dto.DishResponse;
import com.plazoleta.plazoleta.application.mapper.DishMapper;
import com.plazoleta.plazoleta.domain.model.Dish;
import com.plazoleta.plazoleta.domain.repository.DishRepository;
import com.plazoleta.plazoleta.domain.service.DishDomainService;
import com.plazoleta.plazoleta.infrastructure.client.UsersClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateDishUseCase {
    private final DishRepository repo;
    private final DishDomainService domainService;
    private final UsersClient usersClient;

    @Transactional
    public DishResponse handle(CreateDishRequest req) {
        domainService.validarNombre(req.nombre());
        domainService.validarCategoria(req.categoria());

        if (!usersClient.esOwner(req.ownerUserId())) {
            throw new IllegalArgumentException("El usuario no es propietario válido");
        }

        Dish dish = new Dish(null, req.nombre(), req.precio(), req.descripcion(),
                req.urlImagen(), req.categoria(), req.restaurantId());

        Dish saved = repo.save(dish);
        return DishMapper.toResponse(saved);
    }
}
