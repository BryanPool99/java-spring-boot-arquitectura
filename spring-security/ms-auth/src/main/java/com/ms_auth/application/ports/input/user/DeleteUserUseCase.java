package com.ms_auth.application.ports.input.user;

import reactor.core.publisher.Mono;

public interface DeleteUserUseCase {
    Mono<Void> deleteUser(Integer id);
}
