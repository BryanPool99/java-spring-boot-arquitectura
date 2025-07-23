package com.ms_auth.application.ports.input.auth.dto.request;

import com.ms_auth.utils.constants.Constants;
import jakarta.validation.constraints.Email;

import java.util.Objects;

public record RegisterUserCommand(
        String username,
        @Email String email,
        String password
) {
    public RegisterUserCommand {
        Objects.requireNonNull(username, Constants.MSG_USERNAME_NULL);
        Objects.requireNonNull(email, Constants.MSG_EMAIL_NULL);
        Objects.requireNonNull(password, Constants.MSG_PASSWORD_NULL);

        if (username.trim().isEmpty()) {
            throw new IllegalArgumentException(Constants.MSG_USERNAME_EMPTY);
        }
        if (email.trim().isEmpty()) {
            throw new IllegalArgumentException(Constants.MSG_EMAIL_EMPTY);
        }
        if (password.trim().isEmpty()) {
            throw new IllegalArgumentException(Constants.MSG_PASSWORD_EMPTY);
        }
    }
}
