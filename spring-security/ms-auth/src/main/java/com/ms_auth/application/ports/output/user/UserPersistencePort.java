package com.ms_auth.application.ports.output.user;

import com.ms_auth.domain.model.User;
import reactor.core.publisher.Mono;

public interface UserPersistencePort {

    Mono<User> findByUsername(String username);

    Mono<User> findByEmail(String email);

    Mono<Boolean> existsUserWithUsernameOrEmail(String username,String email);

    Mono<User> saveUser(User user);
}
