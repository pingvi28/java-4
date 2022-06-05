package ru.kpfu.itis.kashapova.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.kashapova.form.LoginForm;
import ru.kpfu.itis.kashapova.service.TokenService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@RestController
@PreAuthorize("permitAll()")
public class TokenController {
    Logger logger = LoggerFactory.getLogger(CustomErrorController.class);

    @Autowired
    private TokenService tokenService;

    @GetMapping("/signIn/token")
    public String authenticateByToken(String code,String email, HttpServletRequest request) throws ServletException {
        LoginForm loginForm = tokenService.authenticate(code);
        logger.warn(email);
        request.login(loginForm.getEmail(), loginForm.getPassword());
        return "redirect:/profile";
    }
}
