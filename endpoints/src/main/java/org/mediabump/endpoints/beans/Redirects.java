package org.mediabump.endpoints.beans;

import org.mediabump.auth.domain.tools.provider.Credentials;
import org.mediabump.endpoints.exceptions.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class Redirects {

    @ExceptionHandler(UnauthorizedException.class)
    public String handleUnauthorized(
            UnauthorizedException exception,
            RedirectAttributes redirectAttributes) {
        return "redirect:" + Credentials.getString(Credentials.Setting.ENDPOINTS_LOGIN_URI);
    }

}
