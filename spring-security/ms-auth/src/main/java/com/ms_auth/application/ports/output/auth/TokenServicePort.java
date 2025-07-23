package com.ms_auth.application.ports.output.auth;

import com.ms_auth.application.ports.input.auth.dto.response.AuthResponse;
import com.ms_auth.domain.model.User;
import reactor.core.publisher.Mono;

public interface TokenServicePort {

    Mono<AuthResponse> generateToken(User user);

    Mono<Boolean> validateToken(String token);

    Mono<AuthResponse> refreshToken(String token);
}
