package com.plazoleta.plazoleta.application.usecase;

import com.plazoleta.plazoleta.application.dto.CreateRestaurantRequest;
import com.plazoleta.plazoleta.application.dto.RestaurantResponse;
import com.plazoleta.plazoleta.application.mapper.RestaurantMapper;
import com.plazoleta.plazoleta.domain.model.Restaurant;
import com.plazoleta.plazoleta.domain.repository.RestaurantRepository;
import com.plazoleta.plazoleta.domain.service.RestaurantDomainService;
import com.plazoleta.plazoleta.infrastructure.client.UsersClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateRestaurantUseCase {
    private final RestaurantRepository repo;
    private final RestaurantDomainService domainService;
    private final UsersClient usersClient;

    @Transactional
    public RestaurantResponse handle(CreateRestaurantRequest req) {
        domainService.validarNombre(req.nombre());
        if (!usersClient.esOwner(req.ownerUserId())) {
            throw new IllegalArgumentException("El usuario no es propietario válido");
        }

        Restaurant restaurant = new Restaurant(
                null,
                req.nombre(),
                req.nit(),
                req.direccion(),
                req.telefono(),
                req.urlLogo(),
                req.ownerUserId());

        Restaurant saved = repo.save(restaurant);
        return RestaurantMapper.toResponse(saved);
    }
}
