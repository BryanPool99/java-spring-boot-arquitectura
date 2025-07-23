package com.ms_auth.application.ports.input.user;

import com.ms_auth.domain.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GetUserUseCase {
    Mono<User> getUserById(Integer id);
    Flux<User> getAllUsers();
}
