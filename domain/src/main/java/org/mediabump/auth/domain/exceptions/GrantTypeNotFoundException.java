package org.mediabump.auth.domain.exceptions;

public class GrantTypeNotFoundException extends RuntimeException {
    public GrantTypeNotFoundException(String message) {
        super(message);
    }
}
