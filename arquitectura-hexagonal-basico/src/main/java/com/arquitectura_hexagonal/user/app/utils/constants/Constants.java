package com.arquitectura_hexagonal.user.app.utils.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {
    public static final String MSG_EMAIL_NULL = "Email no puede ser nulo";
    public static final String MSG_NAME_NULL = "Nombre no puede ser nulo";
    public static final String MSG_INVALID_EMAIL_FORMAT = "Formato de email inválido";
    public static final String MSG_NAME_EMPTY = "Nombre no puede estar vacío";
}
