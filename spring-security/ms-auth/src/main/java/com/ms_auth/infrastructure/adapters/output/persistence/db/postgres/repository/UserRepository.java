package com.ms_auth.infrastructure.adapters.output.persistence.db.postgres.repository;

import com.ms_auth.infrastructure.adapters.output.persistence.db.postgres.entity.UserEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends R2dbcRepository<UserEntity, Integer> {

    Mono<UserEntity> findByUsername(String username);

    Mono<UserEntity> findByEmail(String email);
}
