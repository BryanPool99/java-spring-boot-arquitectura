package com.arquitectura_hexagonal.user.app.infrastructure.adapters.input.rest.dto.response;

import com.arquitectura_hexagonal.user.app.application.ports.input.dto.UserDto;

public record UserResponse(Integer id, String name, String email) {

    public static UserResponse fromDto(UserDto applicationDto){
        return new UserResponse(
                applicationDto.id(),
                applicationDto.name(),
                applicationDto.email()
        );
    }
}
