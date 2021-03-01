package org.mediabump.endpoints.endpoints.listusers;

import org.mediabump.auth.domain.models.Session;
import org.mediabump.endpoints.SessionResolver;
import org.mediabump.usecases.repository.UserRepository;
import org.mediabump.usecases.usecases.internal.listusers.ListUsersUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ListUsersEndpoint {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public String dashboard(Model model, HttpServletRequest request) {
        ListUsersPresenter presenter = new ListUsersPresenter(model);
        new ListUsersUseCase(
                userRepository,
                presenter,
                SessionResolver.getSession(request)
        ).get();
        return presenter.getPage();
    }

}
