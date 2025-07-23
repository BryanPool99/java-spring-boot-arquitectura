package com.ms_auth.infrastructure.adapters.output.persistence.db.postgres.mapper;

import com.ms_auth.domain.model.User;
import com.ms_auth.infrastructure.adapters.output.persistence.db.postgres.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserPersistenceMapper {
    @Mapping(target = "userId", source = "id")
    User toUserFromUserEntity(UserEntity userEntity);

    @Mapping(target = "id", source = "userId")
    UserEntity toUserEntityFromUser(User user);
}
