package com.ms_auth.application.ports.input.user;

import com.ms_auth.domain.model.User;
import reactor.core.publisher.Mono;

public interface UpdateUserUseCase {
    Mono<User> updateUser(Integer id, User user);
}
