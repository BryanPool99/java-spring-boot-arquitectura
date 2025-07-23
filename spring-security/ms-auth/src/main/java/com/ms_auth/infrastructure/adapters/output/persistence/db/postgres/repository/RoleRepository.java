package com.ms_auth.infrastructure.adapters.output.persistence.db.postgres.repository;

import com.ms_auth.infrastructure.adapters.output.persistence.db.postgres.entity.RoleEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface RoleRepository extends R2dbcRepository<RoleEntity, Integer> {



}
