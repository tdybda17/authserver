package org.mediabump.endpoints.endpoints;

import org.springframework.ui.Model;

public interface PagePresenter {
    Model getModel();
    String getPage();
}
