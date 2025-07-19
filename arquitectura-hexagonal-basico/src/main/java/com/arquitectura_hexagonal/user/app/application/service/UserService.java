package com.arquitectura_hexagonal.user.app.application.service;

import com.arquitectura_hexagonal.user.app.application.ports.input.UserServicePort;
import com.arquitectura_hexagonal.user.app.application.ports.input.dto.CreateUserCommand;
import com.arquitectura_hexagonal.user.app.application.ports.input.dto.UserDto;
import com.arquitectura_hexagonal.user.app.application.ports.output.UserPersistencePort;
import com.arquitectura_hexagonal.user.app.domain.exception.UserAlreadyExistsException;
import com.arquitectura_hexagonal.user.app.domain.exception.UserNotFoundException;
import com.arquitectura_hexagonal.user.app.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService implements UserServicePort {

    private final UserPersistencePort persistence;

    @Override
    public Mono<UserDto> createUser(CreateUserCommand command) {
        User newUser = command.toDomain();

        return persistence.findByEmail(newUser.email())
                .flatMap(existingUser -> {
                    return Mono.<User>error(new UserAlreadyExistsException(existingUser.email()));
                })
                .switchIfEmpty(Mono.defer(() -> persistence.save(newUser)))
                .map(UserDto::fromDomain);
    }

    @Override
    public Mono<UserDto> getById(Integer id) {
        return persistence.findById(id)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new UserNotFoundException(id))))
                .map(UserDto::fromDomain);
    }

    @Override
    public Mono<UserDto> getByEmail(String email) {
        return persistence.findByEmail(email)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new UserNotFoundException(email))))
                .map(UserDto::fromDomain);
    }

    @Override
    public Flux<UserDto> listAll() {
        return persistence.findAll()
                .map(UserDto::fromDomain);
    }
}
