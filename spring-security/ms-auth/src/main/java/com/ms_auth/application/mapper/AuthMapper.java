package com.ms_auth.application.mapper;

import com.ms_auth.application.ports.input.auth.dto.request.RegisterUserCommand;
import com.ms_auth.application.ports.input.auth.dto.response.RegisterResponse;
import com.ms_auth.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthMapper {

    //@Mapping(target = "userId", ignore = true)
    User toUserFromRegisterUserCommand(RegisterUserCommand registerUserCommand);

    //@Mapping(target = "userId", ignore = true)
    RegisterUserCommand toRegisterUserCommandFromUser(User user);

    //@Mapping(target = "userId", ignore = true)
    User toUserFromRegisterUserResponse(RegisterResponse registerResponse);

    //@Mapping(target = "userId", ignore = true)
    RegisterResponse toRegisterResponseFromUser(User user);
}
