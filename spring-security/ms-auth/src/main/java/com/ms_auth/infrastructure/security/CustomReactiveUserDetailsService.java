package com.ms_auth.infrastructure.security;

import com.ms_auth.application.ports.output.user.UserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CustomReactiveUserDetailsService implements ReactiveUserDetailsService {

    private final UserPersistencePort userPersistencePort;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return userPersistencePort.findByUsername(username)
                .switchIfEmpty(Mono.error(new UsernameNotFoundException("Usuario no encontrado: " + username)))
                .map(SecurityUser::new);
    }
}
