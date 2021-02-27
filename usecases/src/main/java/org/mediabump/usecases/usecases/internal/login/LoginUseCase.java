package org.mediabump.usecases.usecases.internal.login;

import org.mediabump.auth.domain.models.Session;
import org.mediabump.auth.domain.models.User;
import org.mediabump.auth.domain.tools.provider.Credentials;
import org.mediabump.usecases.repository.CookieRepository;
import org.mediabump.usecases.repository.NotFoundException;
import org.mediabump.usecases.repository.SessionRepository;
import org.mediabump.usecases.repository.UserRepository;
import org.mediabump.usecases.request.ValidationException;
import org.mediabump.usecases.tools.HmacSha256;

import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.mediabump.auth.domain.tools.provider.Credentials.Setting.APP_SECRET;

public class LoginUseCase {
    private final HttpServletResponse response;
    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;
    private final CookieRepository cookieRepository;
    private final LoginListener listener;
    private final LoginRequest request;

    public LoginUseCase(
            HttpServletResponse response,
            UserRepository userRepository,
            SessionRepository sessionRepository,
            CookieRepository cookieRepository,
            LoginListener listener,
            LoginRequest request) {
        this.response = response;
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
            Session session = new Session(generateSessionKey(user), user, getSessionExpireDate());
            session = sessionRepository.save(session);
            cookieRepository.setFrom(session, response);
            listener.onSuccess(session);

        } catch (ValidationException e) {
            listener.onValidationError(e.getMessage());
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            listener.onUnexpectedError(e);
        } catch (NotFoundException e) {
            listener.onUserNotFound();
        }

    }

    private String generateSessionKey(User user) {
        return HmacSha256.gen(Credentials.getString(APP_SECRET), user.toString() + UUID.randomUUID());
    }

    private LocalDateTime getSessionExpireDate() {
        return LocalDateTime.now().plusHours(1);
    }

}
