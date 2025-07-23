package com.ms_auth.infrastructure.adapters.output.persistence.db.postgres;

import com.ms_auth.application.ports.output.user.UserPersistencePort;
import com.ms_auth.domain.model.User;
import com.ms_auth.infrastructure.adapters.output.persistence.db.postgres.mapper.UserPersistenceMapper;
import com.ms_auth.infrastructure.adapters.output.persistence.db.postgres.repository.RoleRepository;
import com.ms_auth.infrastructure.adapters.output.persistence.db.postgres.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserPersistencePostgresAdapter implements UserPersistencePort {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final UserPersistenceMapper userPersistenceMapper;

    @Override
    public Mono<User> findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userPersistenceMapper::toUserFromUserEntity);
    }

    @Override
    public Mono<User> findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userPersistenceMapper::toUserFromUserEntity);
    }

    @Override
    public Mono<Boolean> existsUserWithUsernameOrEmail(String username, String email) {
        return null;
    }

    @Override
    public Mono<User> saveUser(User user) {
        return null;
    }
}
