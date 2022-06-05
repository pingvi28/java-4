package ru.kpfu.itis.kashapova.controller;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.kashapova.exception.DuplicateLoginException;
import ru.kpfu.itis.kashapova.exception.NotEqualPasswordsException;
import ru.kpfu.itis.kashapova.form.LoginForm;
import ru.kpfu.itis.kashapova.form.SignUpUserForm;
import ru.kpfu.itis.kashapova.service.UserService;
import ru.kpfu.itis.kashapova.util.BindingResultMessages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping(value = "/signUp")
@PreAuthorize("permitAll()")
public class SignUpController {

    private final UserService userService;
    private final BindingResultMessages bindingResultMessages;


    public SignUpController(UserService userService, BindingResultMessages bindingResultMessages) {
        this.userService = userService;
        this.bindingResultMessages = bindingResultMessages;
    }

    @GetMapping
    public String SignUpPage(Model model) {
        model.addAttribute("userForm", new SignUpUserForm());
        return "pages/sign_up_page";
    }

    @PostMapping
    public String saveNewUser(@Valid SignUpUserForm user, BindingResult result, Model model, HttpServletRequest request) throws ServletException {
        Optional<String> error = bindingResultMessages.getMessageFromError(result, "userForm.UnrepeatableFields");
        if (error.isPresent()) {
            model.addAttribute("repeatableFields", error.get());
            model.addAttribute("userForm", user);
            return "pages/sign_up_page";
        }

        try {
            userService.add(user);
            model.addAttribute("error_message", "Профиль сохранен");
            model.addAttribute("loginForm", new LoginForm(user.getEmail(),user.getPassword()));
            //return "redirect:" + UriComponentsBuilder.fromPath("/").build();
            return "redirect:/signIn";
        } catch (DuplicateKeyException ex) {
            user.setEmail("");
            model.addAttribute("userForm", user);
            model.addAttribute("error_message", "Аккаунт с токой почтой уже существует");
        } catch (NotEqualPasswordsException e) {
            user.setPassword("");
            user.setPasswordRepeat("");
            model.addAttribute("userForm", user);
            model.addAttribute("error_message", "Пароли не совпадают");
        } catch (DuplicateLoginException ex) {
            user.setLogin("");
            model.addAttribute("userForm", user);
            model.addAttribute("error_message", "Аккаунт с таким логином уже существует");
        }
        return "pages/sign_up_page";
    }
}
/**

 if (error.isPresent()) {
 logger.warn("ошибОчка");
 model.addAttribute("repeatableFields", error.get());
 model.addAttribute("userForm", user);
 return "pages/sign_up_page";
 }
 //request.login(user.getEmail(), user.getPassword());
 user.setOauthLinked(false);
 userService.add(user);
 return "redirect:/signIn";**/
