package org.mediabump.endpoints;

import org.mediabump.auth.domain.models.Session;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

public class SessionResolver {

    public static Session getSession(HttpServletRequest request) {
        Object o = request.getAttribute("session");
        if (o == null) {
            return new Session("", null, LocalDateTime.MIN);
        } else {
            return (Session) o;
        }
    }
}
