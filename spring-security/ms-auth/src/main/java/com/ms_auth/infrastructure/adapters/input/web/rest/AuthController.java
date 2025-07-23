package com.ms_auth.infrastructure.adapters.input.web.rest;

import com.ms_auth.application.ports.input.auth.AuthenticateUseCase;
import com.ms_auth.application.ports.input.auth.dto.response.AuthResponse;
import com.ms_auth.infrastructure.adapters.input.web.rest.dto.request.LoginRequest;
import com.ms_auth.infrastructure.adapters.input.web.rest.mapper.RestAdapterMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticateUseCase authenticateUseCase;
    private final RestAdapterMapper mapper;

    @PostMapping("/login")
    public Mono<AuthResponse> login(@RequestBody LoginRequest request) {
        if (request.email().isEmpty() || request.email().isBlank()) {
            var requestWithUsername = mapper.toAuthWithUsernameCommandFromLoginRequest(request);
            return authenticateUseCase.authenticateWithUsername(requestWithUsername);
        }
        var requestWithEmail = mapper.toAuthWithEmailCommandFromLoginRequest(request);
        return authenticateUseCase.authenticateWitEmail(requestWithEmail);
    }
}
