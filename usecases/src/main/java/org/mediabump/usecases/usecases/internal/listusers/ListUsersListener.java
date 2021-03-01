package org.mediabump.usecases.usecases.internal.listusers;

import org.mediabump.auth.domain.models.User;
import org.mediabump.usecases.listener.UnexpectedErrorListener;

import java.util.List;

public interface ListUsersListener extends UnexpectedErrorListener {
    void onSuccess(List<User> userList);
}
