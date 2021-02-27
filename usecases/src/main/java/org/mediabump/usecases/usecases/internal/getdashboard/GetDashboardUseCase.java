package org.mediabump.usecases.usecases.internal.getdashboard;

import org.mediabump.auth.domain.models.Session;
import org.mediabump.auth.domain.models.User;
import org.mediabump.usecases.repository.RepositoryException;
import org.mediabump.usecases.repository.UserRepository;

import java.util.List;

public class GetDashboardUseCase {

    private final UserRepository userRepository;
    private final GetDashboardListener listener;
    private final Session session;

    public GetDashboardUseCase(
            UserRepository userRepository,
            GetDashboardListener listener,
            Session session) {
        this.userRepository = userRepository;
        this.listener = listener;
        this.session = session;
    }

    public void get() {
        try {
            List<User> users = userRepository.all();
            listener.onSuccess(users);
        } catch (RepositoryException e) {
            listener.onUnexpectedError(e);
        }
    }
}
