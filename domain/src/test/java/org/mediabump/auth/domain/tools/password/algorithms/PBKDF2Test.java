package org.mediabump.auth.domain.tools.password.algorithms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mediabump.auth.domain.models.Password;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static org.junit.jupiter.api.Assertions.*;

class PBKDF2Test {

    private PBKDF2 algorithm;

    @BeforeEach
    void setUp() {
        this.algorithm = new PBKDF2();
    }

    @ParameterizedTest
    @ValueSource(strings = {"abc", "_*“@", "12?+Q", ">ß∑∑°∑é‘πœœ", "abcdefghijklm12390481023"})
    void testCanCreateAndValidatePassword(String raw) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Password password = algorithm.createPassword(raw);
        assertEquals(148, password.encode().length());
        assertTrue(password.validate(raw));
    }

}
