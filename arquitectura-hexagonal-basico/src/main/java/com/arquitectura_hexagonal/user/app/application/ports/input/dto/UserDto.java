package com.arquitectura_hexagonal.user.app.application.ports.input.dto;

import com.arquitectura_hexagonal.user.app.domain.model.User;

public record UserDto(Integer id, String name, String email) {
    public static UserDto fromDomain(User user) {
        return new UserDto(user.id(), user.name(), user.email());
    }
}
