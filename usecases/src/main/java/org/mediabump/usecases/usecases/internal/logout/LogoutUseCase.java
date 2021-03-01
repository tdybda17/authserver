package org.mediabump.usecases.usecases.internal.logout;

import org.mediabump.auth.domain.models.Session;
import org.mediabump.usecases.repository.CookieRepository;
import org.mediabump.usecases.repository.RepositoryException;
import org.mediabump.usecases.repository.SessionRepository;

public class LogoutUseCase {

    private final Session session;
    private final SessionRepository sessionRepository;
    private final LogoutListener listener;

    public LogoutUseCase(
            Session session,
            SessionRepository sessionRepository,
            LogoutListener listener) {
        this.session = session;
        this.sessionRepository = sessionRepository;
        this.listener = listener;
    }

    public void logout() {
        if(session == null || session.isExpired()) {
            listener.onUnauthorized();
            return;
        }

        try {
            sessionRepository.delete(session.getKey());
            listener.onSuccess();
        } catch (RepositoryException e) {
            listener.onUnexpectedError(e);
        }
    }
}
