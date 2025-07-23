package com.ms_auth.application.ports.input.auth;

import reactor.core.publisher.Mono;

public interface ValidateTokenUseCase {
    Mono<Boolean> validateToken(String token);
}
