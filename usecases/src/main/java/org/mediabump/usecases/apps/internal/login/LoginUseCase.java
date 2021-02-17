package org.mediabump.usecases.apps.internal.login;

import org.mediabump.auth.domain.models.Session;
import org.mediabump.auth.domain.models.User;
import org.mediabump.usecases.repository.CookieRepository;
import org.mediabump.usecases.repository.SessionRepository;
import org.mediabump.usecases.repository.UserRepository;
import org.mediabump.usecases.request.ValidationException;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDateTime;

public class LoginUseCase {
    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;
    private final CookieRepository cookieRepository;
    private final LoginListener listener;
    private final LoginRequest request;

    public LoginUseCase(
            UserRepository userRepository,
            SessionRepository sessionRepository,
            CookieRepository cookieRepository,
            LoginListener listener,
            LoginRequest request) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.cookieRepository = cookieRepository;
        this.listener = listener;
        this.request = request;
    }

    public void login() {
        try {
            request.validate();
            User user = userRepository.get(request.getEmail(), true);

            if (!user.getPassword().validate(request.getPassword())) {
                listener.onUnauthorized();
                return;
            }
            if (!user.isAdmin()) {
                listener.onForbidden();
                return;
            }
            sessionRepository.clear(user);
            Session session = new Session(generateSessionKey(), user, getSessionExpireDate());

        } catch (ValidationException e) {
            listener.onValidationError(e.getMessage());
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            listener.onUnexpectedError(e);
        }

    }

    private String generateSessionKey() {
        return null;
    }

    private LocalDateTime getSessionExpireDate() {
        return LocalDateTime.now().plusHours(8);
    }

}
