package com.ms_auth.application.ports.input.auth;

import com.ms_auth.application.ports.input.auth.dto.request.RegisterUserCommand;
import com.ms_auth.application.ports.input.auth.dto.response.RegisterResponse;
import reactor.core.publisher.Mono;

public interface RegisterUserUseCase {
    Mono<RegisterResponse> registerUser(RegisterUserCommand registerUserCommand);
}
