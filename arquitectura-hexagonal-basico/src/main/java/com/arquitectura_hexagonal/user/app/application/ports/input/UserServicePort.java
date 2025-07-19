package com.arquitectura_hexagonal.user.app.application.ports.input;

import com.arquitectura_hexagonal.user.app.application.ports.input.dto.CreateUserCommand;
import com.arquitectura_hexagonal.user.app.application.ports.input.dto.UserDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

// Este es un Puerto de Entrada (Driven Port)
public interface UserServicePort {

    Mono<UserDto> createUser(CreateUserCommand command);

    Mono<UserDto> getById(Integer id);

    Mono<UserDto> getByEmail(String email);

    Flux<UserDto> listAll();
}
