package com.arquitectura_hexagonal.user.app.utils.enums;

import java.util.regex.Pattern;

public enum ValidationPatternsEnum {
    EMAIL("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$");

    private final Pattern pattern;

    ValidationPatternsEnum(String regex) {
        this.pattern = Pattern.compile(regex);
    }

    public boolean matches(String input) {
        return input != null && pattern.matcher(input).matches();
    }

    public Pattern getPattern() {
        return pattern;
    }
}
