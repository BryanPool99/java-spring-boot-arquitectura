package com.arquitectura_hexagonal.user.app.application.ports.output;

import com.arquitectura_hexagonal.user.app.domain.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserPersistencePort {

    Mono<User> save(User user);

    Mono<User> findById(Integer id);

    Mono<User> findByEmail(String email);

    Flux<User> findAll();
}
