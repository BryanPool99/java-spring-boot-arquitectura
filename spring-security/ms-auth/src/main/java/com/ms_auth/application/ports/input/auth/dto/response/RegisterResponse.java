package com.ms_auth.application.ports.input.auth.dto.response;

import com.ms_auth.domain.model.Role;
import com.ms_auth.utils.constants.Constants;

import java.util.Objects;
import java.util.Set;

public record RegisterResponse(
        Integer userId,
        String username,
        String email,
        Set<Role> roles
) {
    public RegisterResponse {
        Objects.requireNonNull(userId, Constants.MSG_USER_ID_NULL);
        Objects.requireNonNull(username, Constants.MSG_USERNAME_NULL);
        Objects.requireNonNull(email, Constants.MSG_EMAIL_NULL);

        if (username.trim().isEmpty()) {
            throw new IllegalArgumentException(Constants.MSG_USERNAME_EMPTY);
        }
        if (email.trim().isEmpty()) {
            throw new IllegalArgumentException(Constants.MSG_EMAIL_EMPTY);
        }
        if (roles==null) {
            roles = Set.of(Role.ROLE_USER); // Por defecto solo USER para registro
        }
    }
}
