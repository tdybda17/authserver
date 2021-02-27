package org.mediabump.endpoints.endpoints.login;
import org.mediabump.auth.domain.models.Session;
import org.mediabump.usecases.repository.CookieRepository;
import org.mediabump.usecases.repository.SessionRepository;
import org.mediabump.usecases.repository.UserRepository;
import org.mediabump.usecases.usecases.internal.login.LoginRequest;
import org.mediabump.usecases.usecases.internal.login.LoginUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginEndpoint {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CookieRepository cookieRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @GetMapping("/login")
    public String loginPage(
            HttpServletRequest request) {
        return "login";
    }

    @RequestMapping(
            value = "/login",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    public RedirectView login(
            Model model,
            @RequestBody MultiValueMap<String, String> loginData,
            HttpServletResponse response,
            HttpServletRequest request) {
        LoginPresenter presenter = new LoginPresenter(model);
        LoginRequest _request = new LoginRequest(loginData.toSingleValueMap());
        new LoginUseCase(
                response,
                userRepository,
                sessionRepository,
                cookieRepository,
                presenter,
                _request
        ).login();

        return new RedirectView(presenter.getPage());
    }
}
