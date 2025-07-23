package com.ms_auth.application.ports.input.auth.dto.request;

import com.ms_auth.utils.constants.Constants;

import java.util.Objects;

public record AuthWithUsernameCommand(
        String username,
        String password
) {
    public AuthWithUsernameCommand {
        Objects.requireNonNull(username, Constants.MSG_USERNAME_NULL);
        Objects.requireNonNull(password, Constants.MSG_PASSWORD_NULL);

        if (username.trim().isEmpty()) {
            throw new IllegalArgumentException(Constants.MSG_USERNAME_EMPTY);
        }
        if (password.trim().isEmpty()) {
            throw new IllegalArgumentException(Constants.MSG_PASSWORD_EMPTY);
        }
    }
}
