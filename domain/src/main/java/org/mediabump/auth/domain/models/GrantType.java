package org.mediabump.auth.domain.models;

import org.mediabump.auth.domain.exceptions.GrantTypeNotFoundException;

import java.util.Arrays;

public enum GrantType {
    AUTHORIZATION_CODE("AUTHORIZATION_CODE");

    private final String rawName;

    GrantType(String rawName) {
        this.rawName = rawName;
    }

    public String getRawName() {
        return rawName;
    }

    public static boolean isValid(String type) {
        final String checkType = type.toUpperCase();
        return Arrays.stream(GrantType.values()).anyMatch(e -> e.getRawName().equals(checkType));
    }

    public static GrantType from(String type) {
        final String checkType = type.toUpperCase();
        return Arrays.stream(GrantType.values())
                .filter(e -> e.getRawName().equals(checkType))
                .findFirst()
                .orElseThrow(() -> new GrantTypeNotFoundException("No grant type for string: " + checkType));
    }

}
