package com.ms_auth.infrastructure.security;

import com.ms_auth.application.ports.input.auth.dto.response.AuthResponse;
import com.ms_auth.application.ports.output.auth.TokenServicePort;
import com.ms_auth.domain.model.Role;
import com.ms_auth.domain.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtTokenAdapter implements TokenServicePort {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private Long jwtExpiration;

    private final ReactiveUserDetailsService userDetailsService;

    @Override
    public Mono<AuthResponse> generateToken(User user) {
        log.info("Start the method generateToken with user {}",user.username());
        Map<String, Object> claims = new HashMap<>();
        List<String> roleNames = user.roles().stream()
                .map(Role::name)
                .collect(Collectors.toList());
        claims.put("roles", roleNames);
        claims.put("userId", user.userId());

        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + jwtExpiration);

        String token = Jwts.builder()
                .claims(claims)
                .subject(user.username())
                .issuedAt(now)
                .expiration(expirationDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
        return Mono.just(new AuthResponse(token, user.username(), user.email(), user.userId(), user.roles()));
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public Mono<Boolean> validateToken(String token) {
        return Mono.just(extractAllClaims(token))
                .map(claims -> !isTokenExpired(claims))
                .onErrorResume(e -> {
                    log.error("error al validar token", e.getMessage());
                    return Mono.just(false);
                });
    }

    private Boolean isTokenExpired(Claims claims) {
        final Date expiration = claims.getExpiration();
        return expiration.before(new Date());
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    @Override
    public Mono<AuthResponse> refreshToken(String token) {
        return Mono.just(extractAllClaims(token))
                .flatMap(claims -> {
                    if (isTokenExpired(claims)) {
                        return Mono.error(new RuntimeException("Token ha expirado y no puede ser refrescado."));
                    }

                    String username = extractUsername(claims);

                    return userDetailsService.findByUsername(username)
                            .switchIfEmpty(Mono.error(new RuntimeException("Usuario no encontrado para refrescar: " + username)))
                            .flatMap(userDetails -> {
                                SecurityUser securityUser = (SecurityUser) userDetails;
                                User userToRefresh = securityUser.getUser();
                                return generateToken(userToRefresh);
                            });
                });
    }

    private String extractUsername(Claims claims) {
        return claims.getSubject();
    }
}
