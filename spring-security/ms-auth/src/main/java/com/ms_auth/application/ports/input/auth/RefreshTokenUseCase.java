package com.ms_auth.application.ports.input.auth;

import com.ms_auth.application.ports.input.auth.dto.response.AuthResponse;
import reactor.core.publisher.Mono;

public interface RefreshTokenUseCase {
    Mono<AuthResponse> refreshToken(String token);
}
