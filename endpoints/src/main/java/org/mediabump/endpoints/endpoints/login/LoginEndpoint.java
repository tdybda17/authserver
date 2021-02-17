package org.mediabump.endpoints.endpoints.login;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginEndpoint {

    @GetMapping("/login")
    public String loginPage(HttpSession session, @CookieValue(name = "_q", required = false) String _session) {
        System.out.println(_session);
        return "login";
    }

    @RequestMapping(
            value = "/login",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    public String login(@RequestBody MultiValueMap<String, String> loginData, HttpServletResponse response) {
        Cookie cookie = new Cookie("_q", "myvalue");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(3600);
        response.addCookie(cookie);
        return "login";
    }
}
