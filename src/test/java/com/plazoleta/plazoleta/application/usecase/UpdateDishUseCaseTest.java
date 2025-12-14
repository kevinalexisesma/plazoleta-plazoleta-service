package com.plazoleta.plazoleta.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.plazoleta.plazoleta.application.dto.DishResponse;
import com.plazoleta.plazoleta.application.dto.UpdateDishRequest;
import com.plazoleta.plazoleta.domain.model.Dish;
import com.plazoleta.plazoleta.domain.repository.DishRepository;
import com.plazoleta.plazoleta.infrastructure.client.UsersClient;

@ExtendWith(MockitoExtension.class)
class UpdateDishUseCaseTest {

    @Mock
    DishRepository repo;
    @Mock
    UsersClient usersClient;
    @InjectMocks
    UpdateDishUseCase useCase;

    @Test
    void actualizaPlato_ok() {
        Dish dish = new Dish(1L, "Arroz con pollo", 15000.0, "Plato típico",
                "http://logo.com/arroz.png", "FUERTE", 1L);

        when(usersClient.esOwner(1L)).thenReturn(true);
        when(repo.findById(1L)).thenReturn(Optional.of(dish));
        when(repo.save(any())).thenAnswer(inv -> inv.getArgument(0));

        UpdateDishRequest req = new UpdateDishRequest(1L, 18000.0, "Plato actualizado", 1L);

        DishResponse resp = useCase.handle(req);

        assertEquals(18000.0, resp.precio());
        assertEquals("Plato actualizado", resp.descripcion());
    }

    @Test
    void fallaPorUsuarioNoOwner() {
        when(usersClient.esOwner(2L)).thenReturn(false);

        UpdateDishRequest req = new UpdateDishRequest(1L, 18000.0, "Plato actualizado", 2L);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> useCase.handle(req));

        assertEquals("El usuario no es propietario válido", ex.getMessage());
    }

    @Test
    void fallaPorPlatoNoEncontrado() {
        when(usersClient.esOwner(1L)).thenReturn(true);
        when(repo.findById(99L)).thenReturn(Optional.empty());

        UpdateDishRequest req = new UpdateDishRequest(99L, 18000.0, "Plato actualizado", 1L);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> useCase.handle(req));

        assertEquals("Plato no encontrado", ex.getMessage());
    }
}
