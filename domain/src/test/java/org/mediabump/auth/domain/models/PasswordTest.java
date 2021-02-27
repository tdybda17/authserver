package org.mediabump.auth.domain.models;

import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static org.junit.jupiter.api.Assertions.*;

class PasswordTest {

    @Test
    void testCanCreatePasswordFromHashString() {
        String hash = "68eb12d21790dc46833a0afd8e929556afb835c9c5f85efec412e3c6172234b4";
        String salt = "bcdba5f62aa98a593bd4ccad494c4ed8d49aca6def157da65ee45782d1a821e4";
        String iterations = "150000";
        String alg = "pbkdf2_sha1";
        String combined = alg + "$" + iterations + "$" + salt + "$" + hash;
        Password password = new Password(combined);
        assertEquals(hash, password.getHash());
        assertEquals(salt, password.getSalt());
        assertEquals(Integer.parseInt(iterations), password.getIterations());
    }
}
