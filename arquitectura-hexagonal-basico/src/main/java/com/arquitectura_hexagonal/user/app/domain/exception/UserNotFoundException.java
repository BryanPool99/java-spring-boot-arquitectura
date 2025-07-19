package com.arquitectura_hexagonal.user.app.domain.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(Integer id){
        super("Usuario con id "+id+" no encontrado");
    }

    public UserNotFoundException(String email) {
        super("Usuario con email " + email + " no encontrado");
    }
}
