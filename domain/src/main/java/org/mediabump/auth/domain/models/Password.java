package org.mediabump.auth.domain.models;

import org.mediabump.auth.domain.tools.password.algorithms.HashAlgorithm;
import org.mediabump.auth.domain.tools.password.algorithms.PBKDF2;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.IllegalFormatException;
import java.util.List;

public class Password {
    private String hash;
    private String salt;
    private HashAlgorithm algorithm;
    private int iterations;

    public Password(){}

    /***
     * Hashed password on the form <algorithm>$<iterations>$<salt>$<hash>
     * @param password
     */
    public Password(String password) {
        List<String> split = Arrays.asList(password.split("\\$"));
        if (split.size() == 4) {
            this.hash = split.get(3);
            this.salt = split.get(2);
            this.algorithm = new PBKDF2();
            this.iterations = Integer.parseInt(split.get(1));
        } else {
            throw new IllegalArgumentException("Cannot split password string. s.split('$').size() should be 4");
        }
    }

    public Password(String hash, String salt, HashAlgorithm algorithm, int iterations) {
        this.hash = hash;
        this.salt = salt;
        this.algorithm = algorithm;
        this.iterations = iterations;
    }

    public boolean validate(String rawPassword) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return this.algorithm.validate(rawPassword, this);
    }

    public String getHash() {
        return hash;
    }

    public String getSalt() {
        return salt;
    }

    public HashAlgorithm getAlgorithm() {
        return algorithm;
    }

    public int getIterations() {
        return iterations;
    }

    public String encode() {
        return this.algorithm.getId() + '$' +
                this.iterations + '$' +
                this.salt + '$' +
                this.hash;
    }
}
