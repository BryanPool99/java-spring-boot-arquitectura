package com.arquitectura_hexagonal.user.app.application.ports.input.dto;

import com.arquitectura_hexagonal.user.app.domain.model.User;
import com.arquitectura_hexagonal.user.app.utils.constants.Constants;
import com.arquitectura_hexagonal.user.app.utils.enums.ValidationPatternsEnum;

import java.util.Objects;

public record CreateUserCommand(String name, String email) {
    public CreateUserCommand {
        Objects.requireNonNull(name, Constants.MSG_NAME_NULL);
        Objects.requireNonNull(email, Constants.MSG_EMAIL_NULL);

        if(!ValidationPatternsEnum.EMAIL.matches(email)){
            throw new IllegalArgumentException(Constants.MSG_INVALID_EMAIL_FORMAT);
        }

        if(name.trim().isEmpty()){
            throw new IllegalArgumentException(Constants.MSG_NAME_EMPTY);
        }
    }

    public User toDomain() {
        return new User(null, this.name, this.email);
    }
}
