package com.ms_auth.application.ports.input.user;

import com.ms_auth.domain.model.User;
import reactor.core.publisher.Mono;

public interface CreateUserUseCase {
    Mono<User> createUser(User user);
}
