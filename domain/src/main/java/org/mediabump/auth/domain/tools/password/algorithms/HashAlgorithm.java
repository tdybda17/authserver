package org.mediabump.auth.domain.tools.password.algorithms;

import org.mediabump.auth.domain.models.Password;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface HashAlgorithm {
    String getId();
    Password createPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException;
    boolean validate(String rawPassword, Password goodHash)
            throws NoSuchAlgorithmException, InvalidKeySpecException;
}
