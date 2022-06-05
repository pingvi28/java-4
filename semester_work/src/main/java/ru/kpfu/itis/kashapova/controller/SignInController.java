package ru.kpfu.itis.kashapova.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.kashapova.exception.NotEqualPasswordsException;
import ru.kpfu.itis.kashapova.exception.NotFoundException;
import ru.kpfu.itis.kashapova.form.LoginForm;
import ru.kpfu.itis.kashapova.service.UserService;
import ru.kpfu.itis.kashapova.util.BindingResultMessages;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@PreAuthorize("permitAll()")
@RequestMapping(value = "/signIn")
public class SignInController {

    @Autowired
    private UserService userService;
    private final BindingResultMessages bindingResultMessages;
    private final Logger logger = LoggerFactory.getLogger(CustomErrorController.class);

    public SignInController(UserService userService, BindingResultMessages bindingResultMessages) {
        this.userService = userService;
        this.bindingResultMessages = bindingResultMessages;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String SignInPage(Model model) {
        if(!model.containsAttribute("loginForm")){
            model.addAttribute("loginForm", new LoginForm());
        }
        logger.warn("sdas");

        return "pages/sign_in_page";
    }

    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("isAnonymous()")
    public String login(@Valid LoginForm loginForm, BindingResult result, Model model) {
        Optional<String> messageFromError = bindingResultMessages.getMessageFromError(result, "loginForm.UnrepeatableFields");
        if (messageFromError.isPresent()) {
            model.addAttribute("repeatableFields", messageFromError.get());
            model.addAttribute("loginForm", loginForm);
            return "pages/sign_in_page";
        }

        try {
            userService.authenticate(loginForm.getEmail(), loginForm.getPassword());
            return "redirect:/profile";
            //return "pages/sign_in_page";
        } catch (NotFoundException ex) {
            model.addAttribute("loginForm", loginForm);
            if (loginForm.getPassword() != null) {
                model.addAttribute("error_message", "Аккаунт с токой почтой не существует");
            }
        } catch (NotEqualPasswordsException e) {
            loginForm.setPassword("");
            model.addAttribute("loginForm", loginForm);
            model.addAttribute("error_message", "Пароли не совпадают");
        }

        return "pages/sign_in_page";
    }
}
