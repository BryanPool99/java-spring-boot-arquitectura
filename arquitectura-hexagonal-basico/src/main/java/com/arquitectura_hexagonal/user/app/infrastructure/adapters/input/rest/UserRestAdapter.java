package com.arquitectura_hexagonal.user.app.infrastructure.adapters.input.rest;

import com.arquitectura_hexagonal.user.app.application.ports.input.UserServicePort;
import com.arquitectura_hexagonal.user.app.infrastructure.adapters.input.rest.dto.request.CreateUserRequest;
import com.arquitectura_hexagonal.user.app.infrastructure.adapters.input.rest.dto.response.UserResponse;
import com.arquitectura_hexagonal.user.app.infrastructure.adapters.input.rest.mapper.UserRestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserRestAdapter {

    private final UserServicePort service;
    private final UserRestMapper mapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<UserResponse> listAllUsers(){
        return service.listAll()
                .map(UserResponse::fromDto);
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<UserResponse> getUserById(@PathVariable Integer userId){
        return service.getById(userId)
                .map(UserResponse::fromDto);
    }

    @GetMapping("/email/{email}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<UserResponse> getUserByEmail(@PathVariable String email){
        return service.getByEmail(email)
                .map(UserResponse::fromDto);
    }

    @PostMapping
    public Mono<UserResponse> createUser(@RequestBody CreateUserRequest request){
        var command = mapper.toCommand(request);
        return service.createUser(command)
                .map(UserResponse::fromDto);
    }
}
