package com.arquitectura_hexagonal.user.app.infrastructure.adapters.input.rest.mapper;

import com.arquitectura_hexagonal.user.app.application.ports.input.dto.CreateUserCommand;
import com.arquitectura_hexagonal.user.app.infrastructure.adapters.input.rest.dto.request.CreateUserRequest;
import org.springframework.stereotype.Component;

@Component
public class UserRestMapper {

    public CreateUserCommand toCommand(CreateUserRequest request){
        if (request == null){
            return null;
        }
        return new CreateUserCommand(request.name(), request.email());
    }
}
