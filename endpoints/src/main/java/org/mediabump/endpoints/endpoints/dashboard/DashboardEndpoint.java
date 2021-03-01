package org.mediabump.endpoints.endpoints.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardEndpoint {

    @GetMapping("/dashboard")
    public String get() {
        return "dashboard";
    }

}
