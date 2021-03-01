package org.mediabump.endpoints.endpoints.listusers;

import org.mediabump.auth.domain.models.User;
import org.mediabump.endpoints.endpoints.PagePresenter;
import org.mediabump.usecases.usecases.internal.listusers.ListUsersListener;
import org.springframework.ui.Model;

import java.util.List;

public class ListUsersPresenter implements PagePresenter, ListUsersListener {

    public ListUsersPresenter(Model model) {
        this.model = model;
    }

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
    public void onSuccess(List<User> userList) {
        this.model.addAttribute("users", userList);
        this.page = "users/list";
    }

    @Override
    public void onUnexpectedError(Object object) {

    }
}
