package com.arquitectura_hexagonal.user.app.infrastructure.adapters.output.persitence.mapper;

import com.arquitectura_hexagonal.user.app.domain.model.User;
import com.arquitectura_hexagonal.user.app.infrastructure.adapters.output.persitence.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserPersistenceMapper {

    public  User toDomain(UserEntity entity){
        if (entity == null){
            return null;
        }
        return new User(entity.getId(), entity.getName(), entity.getEmail());
    }

    public  UserEntity toEntity(User domain){
        if(domain == null){
            return null;
        }
        var entity = UserEntity.builder()
                .id(domain.id())
                .name(domain.name())
                .email(domain.email())
                .build();

        return entity;
    }
}
