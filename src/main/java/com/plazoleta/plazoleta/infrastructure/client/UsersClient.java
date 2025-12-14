package com.plazoleta.plazoleta.infrastructure.client;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class UsersClient {
    private final WebClient webClient = WebClient.create("http://localhost:8081");

    public boolean esOwner(Long userId) {
        try {
            return webClient.get()
                    .uri("/users/" + userId)
                    .retrieve()
                    .bodyToMono(UserResponse.class)
                    .map(resp -> "OWNER".equalsIgnoreCase(resp.rol()))
                    .block();
        } catch (Exception e) {
            return false;
        }
    }

    // DTO interno para mapear respuesta del users-service
    private record UserResponse(Long id, String rol) {
    }
}
