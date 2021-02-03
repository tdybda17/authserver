package org.mediabump.usecases.repository;

import org.mediabump.auth.domain.models.Token;
import org.mediabump.auth.domain.models.User;

public interface TokenRepository {
    Token get(String id);
    Token get(User user);
}
