package org.mediabump.usecases.request;

public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }
}
