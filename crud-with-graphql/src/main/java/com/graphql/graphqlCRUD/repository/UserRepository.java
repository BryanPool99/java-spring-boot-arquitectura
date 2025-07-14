package com.graphql.graphqlCRUD.repository;

import com.graphql.graphqlCRUD.entity.UserEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface UserRepository extends R2dbcRepository<UserEntity,Integer> {
}
