package org.mediabump.endpoints.endpoints.logout;

import org.mediabump.endpoints.SessionResolver;
import org.mediabump.usecases.repository.SessionRepository;
import org.mediabump.usecases.usecases.internal.logout.LogoutUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LogoutEndpoint {

    @Autowired
    private SessionRepository sessionRepository;

    @PostMapping("/logout")
    public RedirectView logout(HttpServletRequest request) {
        LogoutPresenter presenter = new LogoutPresenter();
        new LogoutUseCase(
                SessionResolver.getSession(request),
                sessionRepository,
                presenter
        ).logout();
        return new RedirectView(presenter.getPage());
    }

}
