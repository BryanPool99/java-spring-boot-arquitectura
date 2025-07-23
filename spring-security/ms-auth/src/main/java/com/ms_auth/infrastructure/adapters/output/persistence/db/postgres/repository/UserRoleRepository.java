package com.ms_auth.infrastructure.adapters.output.persistence.db.postgres.repository;

import com.ms_auth.infrastructure.adapters.output.persistence.db.postgres.entity.UserRoleEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface UserRoleRepository extends R2dbcRepository<UserRoleEntity, Void> {
}
