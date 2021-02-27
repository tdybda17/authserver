package org.mediabump.endpoints.beans;

import org.mediabump.db.mysql.repositories.SessionRepositoryImpl;
import org.mediabump.db.mysql.repositories.UserRepositoryImpl;
import org.mediabump.endpoints.cookie.CookieRepositoryIml;
import org.mediabump.usecases.repository.CookieRepository;
import org.mediabump.usecases.repository.SessionRepository;
import org.mediabump.usecases.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class Repositories {

    @Bean
    public UserRepository userRepository() {
        return new UserRepositoryImpl();
    }

    @Bean
    public CookieRepository cookieRepository() {
        return new CookieRepositoryIml();
    }

    @Bean
    public SessionRepository sessionRepository() {
        return new SessionRepositoryImpl();
    }

}
