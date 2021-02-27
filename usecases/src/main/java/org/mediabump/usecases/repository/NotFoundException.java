package org.mediabump.usecases.repository;

public class NotFoundException extends RepositoryException {
    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }
}
