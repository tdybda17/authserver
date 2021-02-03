package org.mediabump.usecases.repository;

import org.mediabump.auth.domain.models.Client;

public interface ClientRepository {
    Client get(String id);
    boolean isAuthorized(String clientId, String clientSecret);
}
