package org.mediabump.endpoints.cookie;

import org.mediabump.auth.domain.models.Session;
import org.mediabump.auth.domain.tools.provider.Credentials;
import org.mediabump.usecases.repository.CookieRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.time.LocalDateTime;

public class CookieRepositoryIml implements CookieRepository {
    @Override
    public void setFrom(Session session, HttpServletResponse response) {
        Cookie cookie = new Cookie("_s", session.getKey());
        cookie.setHttpOnly(true);
        Duration duration = Duration.between(LocalDateTime.now(), session.getExpireDate());
        cookie.setMaxAge((int) duration.getSeconds());
        cookie.setSecure(Credentials.getBoolean(Credentials.Setting.APP_SESSION_SECURE_COOKIE));
        response.addCookie(cookie);
    }
}
