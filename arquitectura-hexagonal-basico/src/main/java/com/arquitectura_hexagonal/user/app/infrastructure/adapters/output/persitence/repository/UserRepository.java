package com.arquitectura_hexagonal.user.app.infrastructure.adapters.output.persitence.repository;

import com.arquitectura_hexagonal.user.app.infrastructure.adapters.output.persitence.entity.UserEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends R2dbcRepository<UserEntity,Integer> {
    Mono<UserEntity> findByEmail(String email);
}
