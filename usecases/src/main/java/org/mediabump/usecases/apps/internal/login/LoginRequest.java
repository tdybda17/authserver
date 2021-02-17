package org.mediabump.usecases.apps.internal.login;

import org.mediabump.usecases.request.Request;
import org.mediabump.usecases.request.ValidationException;

public class LoginRequest implements Request {
    private final String email;
    private final String password;

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public void validate() {
        if (this.email.isBlank()) {
            throw new ValidationException("Email should be provided");
        }
        if (this.password.isBlank()) {
            throw new ValidationException("Password should be provided");
        }
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
