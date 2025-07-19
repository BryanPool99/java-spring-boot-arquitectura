package com.arquitectura_hexagonal.user.app.infrastructure.adapters.output.persitence;

import com.arquitectura_hexagonal.user.app.application.ports.output.UserPersistencePort;
import com.arquitectura_hexagonal.user.app.domain.model.User;
import com.arquitectura_hexagonal.user.app.infrastructure.adapters.output.persitence.mapper.UserPersistenceMapper;
import com.arquitectura_hexagonal.user.app.infrastructure.adapters.output.persitence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserPersistencePort {

    private final UserRepository userRepository;
    private final UserPersistenceMapper userPersistenceMapper;

    @Override
    public Mono<User> save(User user) {
        var entityToSave = userPersistenceMapper.toEntity(user);
        return userRepository.save(entityToSave)
                .map(userPersistenceMapper::toDomain);
    }

    @Override
    public Mono<User> findById(Integer id) {
        return userRepository.findById(id)
                .map(userPersistenceMapper::toDomain);
    }

    @Override
    public Mono<User> findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userPersistenceMapper::toDomain);
    }

    @Override
    public Flux<User> findAll() {
        return userRepository.findAll()
                .map(userPersistenceMapper::toDomain);
    }
}
