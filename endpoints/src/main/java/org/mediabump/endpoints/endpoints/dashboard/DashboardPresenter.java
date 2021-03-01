package org.mediabump.endpoints.endpoints.dashboard;

import org.mediabump.endpoints.endpoints.PagePresenter;
import org.springframework.ui.Model;

public class DashboardPresenter implements PagePresenter {

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
}
