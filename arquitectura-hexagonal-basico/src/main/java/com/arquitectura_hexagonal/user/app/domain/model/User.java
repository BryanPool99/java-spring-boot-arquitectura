package com.arquitectura_hexagonal.user.app.domain.model;

import com.arquitectura_hexagonal.user.app.utils.constants.Constants;
import com.arquitectura_hexagonal.user.app.utils.enums.ValidationPatternsEnum;

import java.util.Objects;

public record User(
        Integer id,
        String name,
        String email
) {
    public User {
        Objects.requireNonNull(name, Constants.MSG_NAME_NULL);
        Objects.requireNonNull(email,Constants.MSG_EMAIL_NULL);

        if(!ValidationPatternsEnum.EMAIL.matches(email)){
            throw new IllegalArgumentException(Constants.MSG_INVALID_EMAIL_FORMAT);
        }

        if(name.trim().isEmpty()){
            throw new IllegalArgumentException(Constants.MSG_NAME_EMPTY);
        }
    }

    public User withName(String newName) {
        return new User(this.id, newName, this.email);
    }

    public User withEmail(String newEmail) {
        return new User(this.id, this.name, newEmail);
    }
}
