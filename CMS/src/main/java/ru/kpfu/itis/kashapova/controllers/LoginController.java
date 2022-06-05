package ru.kpfu.itis.kashapova.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.kashapova.models.Login;

@Controller
@RequestMapping("login")
public class LoginController {

    @GetMapping
    @PreAuthorize("isAnonymous()")
    public String login(@RequestParam(required = false) String error, @ModelAttribute("loginForm") Login loginForm, BindingResult result, ModelMap map) {

        map.put("error", error);
        return "security/login";
    }
}
