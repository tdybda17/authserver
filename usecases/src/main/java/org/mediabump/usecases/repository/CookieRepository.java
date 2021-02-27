package org.mediabump.usecases.repository;

import org.mediabump.auth.domain.models.Session;

import javax.servlet.http.HttpServletResponse;

public interface CookieRepository {
    void setFrom(Session session, HttpServletResponse response);
}
