package com.ms_auth.utils.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {
    //MESSAGES FOR MODEL
    public static final String MSG_EMAIL_NULL = "Email no puede ser null";
    public static final String MSG_USERNAME_NULL = "Username no puede ser null";
    public static final String MSG_PASSWORD_NULL = "La contraseñan no puede ser null";
    public static final String MSG_TOKEN_NULL = "Token no puede ser null";
    public static final String MSG_USER_ID_NULL = "UserId no puede ser null";
    public static final String MSG_EMAIL_EMPTY = "Email no puede estar vacio";
    public static final String MSG_USERNAME_EMPTY = "Username no puede estar vacío";
    public static final String MSG_PASSWORD_EMPTY = "Password no puede estar vacio";
    public static final String MSG_TOKEN_EMPTY = "Token no puede estar vacio";
    //MESSAGES FOR EXCEPTIONS
    public static final String MSG_CREDENTIAL_INVALID = "Credenciales inválidas";
    public static final String MSG_TOKEN_INVALID = "Token inválido";
    public static final String MSG_USER_EXISTS = "El usuario ya existe";
}
