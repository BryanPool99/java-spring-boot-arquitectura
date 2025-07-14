package com.graphql.graphqlCRUD.service.imp;

import com.graphql.graphqlCRUD.entity.UserEntity;
import com.graphql.graphqlCRUD.repository.UserRepository;
import com.graphql.graphqlCRUD.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TransactionalOperator transactionalOperator;

    @Override
    public Flux<UserEntity> findAllUser() {
        return userRepository.findAll();
    }

    @Override
    public Mono<UserEntity> finUserById(Integer userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Mono<UserEntity> createUser(UserEntity userEntity) {
        return userRepository.save(userEntity)
                .as(transactionalOperator::transactional);
    }

    @Override
    public Mono<UserEntity> updateUser(Integer userId, UserEntity userEntity) {
        return userRepository.findById(userId)
                .flatMap(userExisting -> updateUserEntity(userExisting,userEntity))
                .as(transactionalOperator::transactional);
    }

    private Mono<UserEntity> updateUserEntity(UserEntity userExisting, UserEntity userEntity) {
        Optional.ofNullable(userEntity.getName())
                .ifPresent(userExisting::setName);
        Optional.ofNullable(userEntity.getEmail())
                .ifPresent(userExisting::setEmail);
        return userRepository.save(userExisting);
    }

    @Override
    public Mono<UserEntity> deleteUser(Integer userId) {
        return userRepository.findById(userId)
                .flatMap(userEntity -> userRepository.deleteById(userId)
                        .thenReturn(userEntity));
    }
}
