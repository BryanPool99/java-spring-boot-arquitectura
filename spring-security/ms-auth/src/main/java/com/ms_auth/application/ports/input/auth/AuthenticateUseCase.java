package com.ms_auth.application.ports.input.auth;

import com.ms_auth.application.ports.input.auth.dto.request.AuthWithEmailCommand;
import com.ms_auth.application.ports.input.auth.dto.request.AuthWithUsernameCommand;
import com.ms_auth.application.ports.input.auth.dto.response.AuthResponse;
import reactor.core.publisher.Mono;

public interface AuthenticateUseCase {
    Mono<AuthResponse> authenticateWithUsername(AuthWithUsernameCommand authWithUsernameCommand);

    Mono<AuthResponse> authenticateWitEmail(AuthWithEmailCommand authWithEmailCommand);
}
