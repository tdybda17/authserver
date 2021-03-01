package org.mediabump.endpoints.endpoints.createuser;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class CreateUserEndpoint {

    @GetMapping("/users/add")
    public String get() {
        return "users/add";
    }

    @PostMapping(value = "/users/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String post(@RequestBody MultiValueMap<String, String> formData) {

        return "users/add";
    }

}
