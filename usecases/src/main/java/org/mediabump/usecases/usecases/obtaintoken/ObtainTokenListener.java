package org.mediabump.usecases.usecases.obtaintoken;

import org.mediabump.usecases.listener.ValidationErrorListener;

public interface ObtainTokenListener extends ValidationErrorListener {
    void onInvalidClient();
    void onInvalidGrantCode();
    void onUnauthorizedClient();
    void onInvalidGrantType();
}
