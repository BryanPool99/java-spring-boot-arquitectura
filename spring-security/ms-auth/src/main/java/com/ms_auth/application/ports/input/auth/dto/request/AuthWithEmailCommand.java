package com.ms_auth.application.ports.input.auth.dto.request;

import com.ms_auth.utils.constants.Constants;

import java.util.Objects;

public record AuthWithEmailCommand(
        String email,
        String password
) {
    public AuthWithEmailCommand {
        Objects.requireNonNull(email, Constants.MSG_EMAIL_NULL);
        Objects.requireNonNull(password, Constants.MSG_PASSWORD_NULL);

        if (email.trim().isEmpty()) {
            throw new IllegalArgumentException(Constants.MSG_EMAIL_EMPTY);
        }
        if (password.trim().isEmpty()) {
            throw new IllegalArgumentException(Constants.MSG_PASSWORD_EMPTY);
        }
    }
}
