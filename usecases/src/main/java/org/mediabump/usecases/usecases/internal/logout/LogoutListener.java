package org.mediabump.usecases.usecases.internal.logout;

import org.mediabump.usecases.listener.UnauthorizedListener;
import org.mediabump.usecases.listener.UnexpectedErrorListener;

public interface LogoutListener extends UnauthorizedListener, UnexpectedErrorListener {
    void onSuccess();
}
