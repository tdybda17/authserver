package org.mediabump.endpoints.endpoints.dashboard;

import org.mediabump.auth.domain.models.User;
import org.mediabump.endpoints.endpoints.PagePresenter;
import org.mediabump.usecases.usecases.internal.getdashboard.GetDashboardListener;
import org.springframework.ui.Model;

import java.util.List;

public class DashboardPresenter implements PagePresenter, GetDashboardListener {

    public DashboardPresenter(Model model) {
        this.model = model;
    }

    private Model model;
    private String page;

    @Override
    public Model getModel() {
        return null;
    }

    @Override
    public String getPage() {
        return null;
    }

    @Override
    public void onSuccess(List<User> userList) {
        this.model.addAttribute("users", userList);
        this.page = "dashboard";
    }

    @Override
    public void onUnexpectedError(Object object) {

    }
}
