package org.mediabump.usecases.repository;

public interface AuthorizationRepository {
    boolean isValid(String grantCode);
}
