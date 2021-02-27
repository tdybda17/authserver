package org.mediabump.usecases.usecases.internal.login;

import org.mediabump.auth.domain.models.Session;
import org.mediabump.usecases.listener.UnauthorizedListener;
import org.mediabump.usecases.listener.UnexpectedErrorListener;
import org.mediabump.usecases.listener.ValidationErrorListener;

public interface LoginListener extends ValidationErrorListener, UnauthorizedListener, UnexpectedErrorListener {
    void onSuccess(Session session);
    void onForbidden();
    void onUserNotFound();
}
