package com.plazoleta.plazoleta.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.plazoleta.plazoleta.application.dto.CreateRestaurantRequest;
import com.plazoleta.plazoleta.application.dto.RestaurantResponse;
import com.plazoleta.plazoleta.domain.model.Restaurant;
import com.plazoleta.plazoleta.domain.repository.RestaurantRepository;
import com.plazoleta.plazoleta.domain.service.RestaurantDomainService;
import com.plazoleta.plazoleta.infrastructure.client.UsersClient;

@ExtendWith(MockitoExtension.class)
class CreateRestaurantUseCaseTest {
    @Mock
    RestaurantRepository repo; // <-- mockeamos el puerto de dominio
    @Mock
    UsersClient usersClient;
    @InjectMocks
    CreateRestaurantUseCase useCase;
    @Spy
    RestaurantDomainService domainService = new RestaurantDomainService();

    @Test
    void creaRestaurante_ok() {
        when(usersClient.esOwner(1L)).thenReturn(true);
        when(repo.save(any())).thenAnswer(inv -> {
            Restaurant r = inv.getArgument(0);
            return new Restaurant(10L, r.getNombre(), r.getNit(),
                    r.getDireccion(), r.getTelefono(), r.getUrlLogo(), r.getOwnerUserId());
        });

        CreateRestaurantRequest req = new CreateRestaurantRequest(
                "Mi Restaurante", "123456", "Calle 10 #20", "+573001234567",
                "http://logo.com/logo.png", 1L);

        RestaurantResponse resp = useCase.handle(req);
        assertEquals("Mi Restaurante", resp.nombre());
        assertEquals(10L, resp.id());
    }
}
