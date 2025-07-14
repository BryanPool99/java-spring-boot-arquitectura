package com.graphql.graphqlCRUD.controller;

import com.graphql.graphqlCRUD.controller.dto.request.UserInputRecord;
import com.graphql.graphqlCRUD.entity.UserEntity;
import com.graphql.graphqlCRUD.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserGraphQlController {

    private final UserService userService;

    @QueryMapping
    public Flux<UserEntity> getAllUsers(){
        log.info("Fetching all users via GraphQL");
        return userService.findAllUser();
    }

    @QueryMapping
    public Mono<UserEntity> getUserById(@Argument Integer userId){
        log.info("Fetching user by ID: {} via GraphQL", userId);
        return userService.finUserById(userId);
    }

    @MutationMapping
    public Mono<UserEntity> createUser(@Argument UserInputRecord useInput){
        log.info("Creating user: {} via GraphQL", useInput.name());
        var newUser = UserEntity.builder()
                .name(useInput.name())
                .email(useInput.email())
                .build();
        return userService.createUser(newUser);
    }

    @MutationMapping
    public Mono<UserEntity> updateUser(@Argument Integer userId,@Argument UserInputRecord updateUserInput){
        log.info("Updating user ID: {} with new data: {} via GraphQL", userId, updateUserInput.name());
        var userToUpdate = UserEntity.builder()
                .id(userId)
                .name(updateUserInput.name())
                .email(updateUserInput.email())
                .build();
        return userService.updateUser(userId,userToUpdate);
    }

    @MutationMapping
    public Mono<UserEntity> deleteUser(@Argument Integer userId){
        log.info("Deleting user by ID: {} via GraphQL", userId);
        return userService.deleteUser(userId);
    }
}
