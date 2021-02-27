package org.mediabump.usecases.usecases.internal.login;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mediabump.auth.domain.models.User;
import org.mediabump.auth.domain.tools.password.algorithms.PBKDF2;
import org.mediabump.usecases.repository.CookieRepository;
import org.mediabump.usecases.repository.NotFoundException;
import org.mediabump.usecases.repository.SessionRepository;
import org.mediabump.usecases.repository.UserRepository;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static org.mockito.Mockito.*;

class LoginUseCaseTest {

    private UserRepository userRepository;
    private SessionRepository sessionRepository;
    private CookieRepository cookieRepository;
    private LoginRequest request;
    private LoginUseCase loginUseCase;
    private LoginListener listener;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        sessionRepository = mock(SessionRepository.class);
        cookieRepository = mock(CookieRepository.class);
        listener = mock(LoginListener.class);
        request = mock(LoginRequest.class);
    }

    @Test
    void testWhenRequestNotValid_ShouldGetValidationError() {
        request = new LoginRequest("", "");
        runUseCase();
        verify(listener).onValidationError(anyString());
        verifyNoMoreInteractions(listener);
    }

    @Test
    void testWhenNoUserFoundByEmail_ShouldGetNotFound() {
        request = new LoginRequest("email", "email");
        when(userRepository.get(anyString(), anyBoolean())).thenThrow(new NotFoundException());
        runUseCase();
        verify(listener).onUserNotFound();
        verifyNoMoreInteractions(listener);
    }

    @Test
    void testCanMakeValidSession() throws InvalidKeySpecException, NoSuchAlgorithmException {
        User user = new User.UserBuilder()
                .setPassword(new PBKDF2().createPassword("qwerty"))
                .setAdmin(true)
                .build();
        request = new LoginRequest("email", "qwerty");
        when(userRepository.get(anyString(), anyBoolean())).thenReturn(user);
        runUseCase();
        verify(listener).onSuccess(anyObject());
        verifyNoMoreInteractions(listener);
    }

    private void runUseCase() {
        new LoginUseCase(
                null,
                userRepository,
                sessionRepository,
                cookieRepository,
                listener,
                request
        ).login();
    }
}