package org.mediabump.usecases.apps.internal.login;

import org.mediabump.usecases.listener.UnauthorizedListener;
import org.mediabump.usecases.listener.UnexpectedErrorListener;
import org.mediabump.usecases.listener.ValidationErrorListener;

public interface LoginListener extends ValidationErrorListener, UnauthorizedListener, UnexpectedErrorListener {
    void onSuccess();
    void onForbidden();
}
