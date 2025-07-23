package com.ms_auth.domain.model;

import com.ms_auth.utils.constants.Constants;

import java.util.Objects;
import java.util.Set;

public record User(
        Integer userId,
        String username,
        String email,
        String password,
        Set<Role> roles
) {
    public User {
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
        if (roles==null) {
            roles = Set.of(Role.ROLE_USER);
        }
    }

    public User(String username, String email, String password, Set<Role> roles) {
        this(null, username, email, password, roles);
    }

    public User withUserId(Integer userId) {
        return new User(userId, username, email, password, roles);
    }
}
