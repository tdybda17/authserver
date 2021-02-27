package org.mediabump.usecases.repository;

import org.mediabump.auth.domain.models.User;

import java.util.List;

public interface UserRepository {
    User save(User user);
    User delete(User user);
    User delete(String id);
    User get(String id);
    User get(String email, boolean withSession);
    List<User> all();
}
