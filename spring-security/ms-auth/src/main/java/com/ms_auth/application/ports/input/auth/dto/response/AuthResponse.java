package com.ms_auth.application.ports.input.auth.dto.response;

import com.ms_auth.domain.model.Role;
import com.ms_auth.utils.constants.Constants;

import java.util.Objects;
import java.util.Set;

public record AuthResponse(
        String token,
        String username,
        Integer userId,
        Set<Role> roles
) {
    public AuthResponse {
        Objects.requireNonNull(token, Constants.MSG_TOKEN_NULL);
        Objects.requireNonNull(username, Constants.MSG_USERNAME_NULL);
        Objects.requireNonNull(userId, Constants.MSG_USER_ID_NULL);

        if (username.trim().isEmpty()) {
            throw new IllegalArgumentException(Constants.MSG_USERNAME_EMPTY);
        }
        if (token.trim().isEmpty()) {
            throw new IllegalArgumentException(Constants.MSG_TOKEN_EMPTY);
        }
        if (roles==null) {
            roles = Set.of();
        }
    }
}
