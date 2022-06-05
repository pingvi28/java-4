package ru.kpfu.itis.kashapova.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@PreAuthorize("permitAll()")
public class DefaultController {

    @GetMapping("/")
    public String getDefaultPage() {
        return "main";
    }
}
