package org.mediabump.usecases.repository;

import java.text.RuleBasedCollator;

public class RepositoryException extends RuntimeException {
    public RepositoryException() {
    }

    public RepositoryException(String message) {
        super(message);
    }
}
