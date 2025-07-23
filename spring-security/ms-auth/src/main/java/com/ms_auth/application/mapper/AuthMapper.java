package com.ms_auth.application.mapper;

import com.ms_auth.application.ports.input.auth.dto.request.RegisterUserCommand;
import com.ms_auth.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthMapper {

    @Mapping(target = "userId",ignore = true)
    User toUserFromRegisterUserCommand(RegisterUserCommand registerUserCommand);
}
