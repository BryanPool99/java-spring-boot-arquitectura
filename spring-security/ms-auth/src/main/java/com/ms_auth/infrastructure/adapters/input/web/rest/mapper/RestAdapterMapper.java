package com.ms_auth.infrastructure.adapters.input.web.rest.mapper;

import com.ms_auth.application.ports.input.auth.dto.request.AuthWithEmailCommand;
import com.ms_auth.application.ports.input.auth.dto.request.AuthWithUsernameCommand;
import com.ms_auth.infrastructure.adapters.input.web.rest.dto.request.LoginRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RestAdapterMapper {

    @Mapping(target = "username", source = "username")
    @Mapping(target = "password", source = "password")
    AuthWithUsernameCommand toAuthWithUsernameCommandFromLoginRequest(LoginRequest loginRequest);

    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", source = "password")
    AuthWithEmailCommand toAuthWithEmailCommandFromLoginRequest(LoginRequest loginRequest);
}
