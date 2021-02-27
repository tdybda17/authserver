package org.mediabump.endpoints.endpoints.login;

import org.mediabump.auth.domain.models.Session;
import org.mediabump.auth.domain.tools.provider.Credentials;
import org.mediabump.endpoints.endpoints.PagePresenter;
import org.mediabump.usecases.usecases.internal.login.LoginListener;
import org.springframework.ui.Model;

public class LoginPresenter implements PagePresenter, LoginListener {

    private String page;
    private Model model;

    public LoginPresenter(Model model) {
        this.model = model;
    }

    @Override
    public Model getModel() {
        return model;
    }

    @Override
    public String getPage() {
        return page;
    }

    @Override
    public void onSuccess(Session session) {
        this.page = Credentials.getString(Credentials.Setting.ENDPOINTS_LOGIN_SUCCESS_REDIRECT);
    }

    @Override
    public void onForbidden() {
        this.page = "login";
        this.model.addAttribute("error", "email or password is wrong");
    }

    @Override
    public void onUserNotFound() {
        this.page = "login";
        this.model.addAttribute("error", "email or password is wrong");
    }

    @Override
    public void onUnauthorized() {
        this.page = "login";
        this.model.addAttribute("error", "email or password is wrong");
    }

    @Override
    public void onUnexpectedError(Object object) {
        this.page = "login";
        this.model.addAttribute("error", "Unexcepted error");
    }

    @Override
    public void onValidationError(String msg) {
        this.page = "login";
        this.model.addAttribute("error", msg);
    }
}
