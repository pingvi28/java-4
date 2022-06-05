package ru.kpfu.itis.kashapova.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;
import ru.kpfu.itis.kashapova.services.RegistrationService;
import ru.kpfu.itis.kashapova.exceptions.NotEqualPasswordsException;
import ru.kpfu.itis.kashapova.models.User;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private RegistrationService registerService;

    @GetMapping
    @PreAuthorize("isAnonymous()")
    public String registration(ModelMap map) {
        map.put("user", new User());
        return "security/registration";
    }

    @PostMapping
    @PreAuthorize("isAnonymous()")
    public String registrationHandler(
            RedirectAttributes redirectAttributes,
            @ModelAttribute("user") @Valid User user,
            BindingResult result,
            ModelMap map
    ) {
        if (!result.hasErrors()) {
            try {
                registerService.registerUser(user);
                return "redirect:" + UriComponentsBuilder.fromPath("/").build();
            } catch (DuplicateKeyException ex) {
                result.rejectValue("email", "entry.duplicate", "Аккаунт с токой почтой уже существует");
            } catch (NotEqualPasswordsException e) {
                result.rejectValue("passwordRepeat", "entry.duplicate", "Пароли не совпадают");
            }
        }
        return "security/registration";
    }
}
