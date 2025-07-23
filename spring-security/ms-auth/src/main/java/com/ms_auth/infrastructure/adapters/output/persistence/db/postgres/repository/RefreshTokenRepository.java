package com.ms_auth.infrastructure.adapters.output.persistence.db.postgres.repository;

import com.ms_auth.infrastructure.adapters.output.persistence.db.postgres.entity.RefreshTokenEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface RefreshTokenRepository extends R2dbcRepository<RefreshTokenEntity, Integer> {
}
