package org.mediabump.usecases.repository;

import org.mediabump.auth.domain.models.Session;
import org.mediabump.auth.domain.models.User;

public interface SessionRepository {
    void clear(User user);
    Session save(Session session);
    Session get(String key);
    void delete(String key);
}
