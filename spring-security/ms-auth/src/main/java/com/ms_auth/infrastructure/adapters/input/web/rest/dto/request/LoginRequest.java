package com.ms_auth.infrastructure.adapters.input.web.rest.dto.request;

public record LoginRequest(
        String email,
        String username,
        String password
) {
}
