package com.arquitectura_hexagonal.user.app.domain.exception;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String email) {
      super("El usuario con email '"+ email +"' ya se encuentra registrado.");
    }
}
