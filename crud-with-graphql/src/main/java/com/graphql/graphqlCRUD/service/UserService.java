package com.graphql.graphqlCRUD.service;

import com.graphql.graphqlCRUD.entity.UserEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
    Flux<UserEntity> findAllUser();
    Mono<UserEntity> finUserById(Integer userId);
    Mono<UserEntity> createUser(UserEntity userEntity);
    Mono<UserEntity> updateUser(Integer userId,UserEntity userEntity);
    Mono<UserEntity> deleteUser(Integer userId);
}
