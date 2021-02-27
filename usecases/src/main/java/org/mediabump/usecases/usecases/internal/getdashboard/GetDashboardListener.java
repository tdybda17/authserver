package org.mediabump.usecases.usecases.internal.getdashboard;

import org.mediabump.auth.domain.models.User;
import org.mediabump.usecases.listener.UnexpectedErrorListener;

import java.util.List;

public interface GetDashboardListener extends UnexpectedErrorListener {
    void onSuccess(List<User> userList);
}
