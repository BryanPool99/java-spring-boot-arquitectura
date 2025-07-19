package com.arquitectura_hexagonal.user.app.infrastructure.adapters.input.rest;

import com.arquitectura_hexagonal.user.app.domain.exception.UserAlreadyExistsException;
import com.arquitectura_hexagonal.user.app.domain.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(
                ex.getMessage(),
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExists(UserAlreadyExistsException ex) {
        ErrorResponse error = new ErrorResponse(
                ex.getMessage(),
                HttpStatus.CONFLICT,
                ZonedDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        ErrorResponse error = new ErrorResponse(
                "Error interno del servidor",
                HttpStatus.INTERNAL_SERVER_ERROR,
                ZonedDateTime.now()
        );
        return ResponseEntity.internalServerError().body(error);
    }

    record ErrorResponse(
            String message,
            HttpStatus status,
            ZonedDateTime timestamp
    ) {}
}
