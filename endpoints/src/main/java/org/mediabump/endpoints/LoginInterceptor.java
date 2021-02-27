package org.mediabump.endpoints;

import org.mediabump.auth.domain.models.Session;
import org.mediabump.auth.domain.tools.provider.Credentials;
import org.mediabump.endpoints.beans.Repositories;
import org.mediabump.endpoints.exceptions.UnauthorizedException;
import org.mediabump.usecases.repository.RepositoryException;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Service
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        if (cookies == null && isLoginPage(request.getRequestURI())) {
            return true;
        }
        if (cookies == null && !isLoginPage(request.getRequestURI())) {
            throw new UnauthorizedException();
        }
        Cookie session = Arrays.stream(request.getCookies())
                .filter(c -> c.getName().equals("_s"))
                .findFirst()
                .orElse(null);
        if (isLoginPage(request.getRequestURI())) {
            return true;
        }

        if (session == null) {
            throw new UnauthorizedException();
        }

        try {
            Session _session = new Repositories().sessionRepository().get(session.getValue());
            if (_session.isExpired()) {
                throw new UnauthorizedException();
            }
            request.setAttribute("session", _session);
            return true;
        } catch (RepositoryException e) {
            throw new UnauthorizedException();
        }
    }

    private boolean isLoginPage(String uri) {
        return uri.equals(Credentials.getString(Credentials.Setting.ENDPOINTS_LOGIN_URI));
    }

}
