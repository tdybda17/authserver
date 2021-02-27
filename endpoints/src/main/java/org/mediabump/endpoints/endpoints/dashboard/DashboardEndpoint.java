package org.mediabump.endpoints.endpoints.dashboard;

import org.mediabump.auth.domain.models.Session;
import org.mediabump.usecases.repository.UserRepository;
import org.mediabump.usecases.usecases.internal.getdashboard.GetDashboardUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DashboardEndpoint {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpServletRequest request) {
        DashboardPresenter presenter = new DashboardPresenter(model);
        new GetDashboardUseCase(
                userRepository,
                presenter,
                (Session) request.getAttribute("session")
        ).get();
        return "dashboard";
    }

}
