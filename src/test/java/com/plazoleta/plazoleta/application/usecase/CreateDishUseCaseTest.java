package com.plazoleta.plazoleta.application.usecase;

import com.plazoleta.plazoleta.application.dto.CreateDishRequest;
import com.plazoleta.plazoleta.application.dto.DishResponse;
import com.plazoleta.plazoleta.domain.model.Dish;
import com.plazoleta.plazoleta.domain.repository.DishRepository;
import com.plazoleta.plazoleta.domain.service.DishDomainService;
import com.plazoleta.plazoleta.infrastructure.client.UsersClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateDishUseCaseTest {

    @Mock
    DishRepository repo;

    @Mock
    UsersClient usersClient;

    @Spy
    DishDomainService domainService = new DishDomainService();

    @InjectMocks
    CreateDishUseCase useCase;

    @Test
    void creaPlato_ok() {
        when(usersClient.esOwner(1L)).thenReturn(true);
        when(repo.save(any())).thenAnswer(inv -> {
            Dish d = inv.getArgument(0);
            return new Dish(10L, d.getNombre(), d.getPrecio(), d.getDescripcion(),
                    d.getUrlImagen(), d.getCategoria(), d.getRestaurantId());
        });

        CreateDishRequest req = new CreateDishRequest(
                "Arroz con pollo", 15000.0, "Plato típico",
                "http://logo.com/arroz.png", "FUERTE", 1L, 1L);

        DishResponse resp = useCase.handle(req);

        assertEquals("Arroz con pollo", resp.nombre());
        assertEquals(10L, resp.id());
        assertEquals("FUERTE", resp.categoria());
    }

    @Test
    void fallaPorNombreSoloNumeros() {
        CreateDishRequest req = new CreateDishRequest(
                "12345", 12000.0, "Plato inválido",
                "http://logo.com/plato.png", "ENTRADA", 1L, 1L);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> useCase.handle(req));

        assertEquals("El nombre no puede ser solo números", ex.getMessage());
    }

    @Test
    void fallaPorCategoriaInvalida() {
        CreateDishRequest req = new CreateDishRequest(
                "Sopa de verduras", 10000.0, "Plato saludable",
                "http://logo.com/sopa.png", "INVALIDA", 1L, 1L);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> useCase.handle(req));

        assertEquals("Categoría inválida", ex.getMessage());
    }

    @Test
    void fallaPorUsuarioNoOwner() {
        CreateDishRequest req = new CreateDishRequest(
                "Carne asada", 20000.0, "Plato fuerte",
                "http://logo.com/carne.png", "FUERTE", 1L, 2L);

        when(usersClient.esOwner(2L)).thenReturn(false);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> useCase.handle(req));

        assertEquals("El usuario no es propietario válido", ex.getMessage());
    }
}
