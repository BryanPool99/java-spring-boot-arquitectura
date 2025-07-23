package com.ms_auth.application.service;

import com.ms_auth.application.mapper.AuthMapper;
import com.ms_auth.application.ports.input.auth.AuthenticateUseCase;
import com.ms_auth.application.ports.input.auth.RefreshTokenUseCase;
import com.ms_auth.application.ports.input.auth.RegisterUserUseCase;
import com.ms_auth.application.ports.input.auth.ValidateTokenUseCase;
import com.ms_auth.application.ports.input.auth.dto.request.AuthWithEmailCommand;
import com.ms_auth.application.ports.input.auth.dto.request.AuthWithUsernameCommand;
import com.ms_auth.application.ports.input.auth.dto.request.RegisterUserCommand;
import com.ms_auth.application.ports.input.auth.dto.response.AuthResponse;
import com.ms_auth.application.ports.input.auth.dto.response.RegisterResponse;
import com.ms_auth.application.ports.output.auth.TokenServicePort;
import com.ms_auth.application.ports.output.user.UserPersistencePort;
import com.ms_auth.domain.model.Role;
import com.ms_auth.domain.model.User;
import com.ms_auth.utils.constants.Constants;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements
        AuthenticateUseCase, RefreshTokenUseCase, ValidateTokenUseCase, RegisterUserUseCase {

    private final UserPersistencePort userPersistencePort;
    private final TokenServicePort tokenServicePort;
    private final PasswordEncoder passwordEncoder;

    private final AuthMapper authMapper;

    @Override
    public Mono<AuthResponse> authenticateWithUsername(AuthWithUsernameCommand authWithUsernameCommand) {
        log.info("Start the method authenticateWithUsername with username {}", authWithUsernameCommand.username());
        return userPersistencePort.findByUsername(authWithUsernameCommand.username())
                .doOnNext(user -> log.info("user encontrado con user id {}", user.userId()))
                .filter(user -> passwordEncoder.matches(authWithUsernameCommand.password(), user.password()))
                .switchIfEmpty(Mono.error(new BadCredentialsException(Constants.MSG_CREDENTIAL_INVALID)))
                .flatMap(user -> tokenServicePort.generateToken(user))
                .doOnError(e ->
                        log.error("Error durante la autenticación para el usuario {}: {}", authWithUsernameCommand.username(),
                                e.getMessage()))
                .doOnSuccess(authResponse -> log.info("end of method authenticateWithUsername"));

    }

    /*
    @Override
    public Mono<AuthResponse> authenticateWithUsername(AuthWithUsernameCommand authWithUsernameCommand) {
        log.info("Inicio del método authenticateWithUsername para el usuario: {}", authWithUsernameCommand.username());

        return userPersistencePort.findByUsername(authWithUsernameCommand.username())
                .doOnNext(user -> log.info("Usuario encontrado en la base de datos: {}", user.username())) // Log para cuando se encuentra el usuario
                .switchIfEmpty(Mono.defer(() -> { // Usar Mono.defer para que el log se ejecute solo si es necesario
                    log.info("Usuario no encontrado: {}. Lanzando BadCredentialsException.", authWithUsernameCommand.username());
                    return Mono.error(new BadCredentialsException(Constants.MSG_CREDENTIAL_INVALID));
                }))
                .filter(user -> {
                    boolean passwordMatches = passwordEncoder.matches(authWithUsernameCommand.password(), user.password());
                    if (passwordMatches) {
                        log.info("Contraseña correcta para el usuario: {}", user.username());
                    } else {
                        log.info("Contraseña incorrecta para el usuario: {}. Lanzando BadCredentialsException.", user.username());
                    }
                    return passwordMatches;
                })
                .switchIfEmpty(Mono.defer(() -> { // Este switchIfEmpty se ejecutará si el filter devuelve false (contraseña no coincide)
                    log.info("La contraseña no coincide para el usuario: {}. Esto ya fue manejado por el filtro.", authWithUsernameCommand.username());
                    // No lanzamos una nueva excepción aquí, ya que el filter debería haber gestionado esto.
                    // Es solo para fines de log, si el flujo se salta el error del filter.
                    return Mono.error(new BadCredentialsException(Constants.MSG_CREDENTIAL_INVALID));
                }))
                .flatMap(user -> {
                    log.info("Generando token para el usuario: {}", user.username());
                    return tokenServicePort.generateToken(user);
                })
                .doOnError(e -> log.error("Error durante la autenticación para el usuario {}: {}", authWithUsernameCommand.username(), e.getMessage())); // Log de errores generales
    }
    */
    @Override
    public Mono<AuthResponse> authenticateWitEmail(AuthWithEmailCommand authWithEmailCommand) {
        log.info("Start the method authenticateWitEmail with email {}", authWithEmailCommand.email());
        return userPersistencePort.findByEmail(authWithEmailCommand.email())
                .switchIfEmpty(Mono.error(new BadCredentialsException(Constants.MSG_CREDENTIAL_INVALID)))
                .filter(user -> passwordEncoder.matches(authWithEmailCommand.password(), user.password()))
                .flatMap(user -> tokenServicePort.generateToken(user));
    }

    @Override
    public Mono<AuthResponse> refreshToken(String token) {
        log.info("Start the method refreshToken");
        return tokenServicePort.validateToken(token)
                .flatMap(isValid -> {
                    if (isValid) {
                        return tokenServicePort.refreshToken(token);
                    }
                    return Mono.error(new JwtException(Constants.MSG_TOKEN_INVALID));
                });

    }

    @Override
    public Mono<Boolean> validateToken(String token) {
        log.info("Start the method validateToken");
        return tokenServicePort.validateToken(token)
                .onErrorReturn(false);
    }

    @Override
    public Mono<RegisterResponse> registerUser(RegisterUserCommand registerUserCommand) {
        log.info("Start the method registerUser");
        return userPersistencePort.existsUserWithUsernameOrEmail(registerUserCommand.username(),
                        registerUserCommand.email())
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new IllegalArgumentException(Constants.MSG_USER_EXISTS));
                    }
                    var newUser = new User(registerUserCommand.username(),
                            passwordEncoder.encode(registerUserCommand.password()),
                            registerUserCommand.email(), Set.of(Role.ROLE_USER)
                    );
                    return userPersistencePort.saveUser(newUser);
                })
                .map(authMapper::toRegisterResponseFromUser);
    }
}
