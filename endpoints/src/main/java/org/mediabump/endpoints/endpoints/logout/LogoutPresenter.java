package org.mediabump.endpoints.endpoints.logout;

import org.mediabump.auth.domain.tools.provider.Credentials;
import org.mediabump.endpoints.endpoints.PagePresenter;
import org.mediabump.usecases.usecases.internal.logout.LogoutListener;
import org.springframework.ui.Model;

import static org.mediabump.auth.domain.tools.provider.Credentials.Setting.ENDPOINTS_LOGIN_URI;

public class LogoutPresenter implements PagePresenter, LogoutListener {

    private Model model;
    private String page;

    @Override
    public Model getModel() {
        return model;
    }

    @Override
    public String getPage() {
        return page;
    }

    @Override
    public void onUnauthorized() {
        this.page = "error/401";
    }

    @Override
    public void onUnexpectedError(Object object) {
        this.page = "error/500";
    }

    @Override
    public void onSuccess() {
        this.page = Credentials.getString(ENDPOINTS_LOGIN_URI);
    }
}
