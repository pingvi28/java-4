package ru.kpfu.itis.kashapova.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;
import ru.kpfu.itis.kashapova.dto.UserDto;
import ru.kpfu.itis.kashapova.entity.UserStatistic;
import ru.kpfu.itis.kashapova.form.SignUpUserForm;
import ru.kpfu.itis.kashapova.service.UserService;

import java.security.Principal;
import java.util.Optional;

@Controller
@PreAuthorize("isAuthenticated()")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public String getMyPage(Model model, Principal principal) {
        model.addAttribute("user", userService.getByEmail(principal.getName()).get());
        return "pages/profile_page";
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/users/{email}")
    public String getUserPage(Model model, @PathVariable("email") String email) {

        UserStatistic userDto = userService.getUserStatistic(email);
        model.addAttribute("user", userDto);
        return "pages/user_page";
    }

    @PostMapping("/profile/delete")
    public String deleteProfile(Principal principal) {
        userService.delete(principal.getName());
        return "redirect:/logout";
    }

    @GetMapping("/profile/update")
    public String getUpdatePage(Model model) {
        model.addAttribute("userForm", new SignUpUserForm());
        return "pages/update_user_profile_page";
    }

    @PostMapping("/profile/update")
    public String updateProfile(Principal principal, SignUpUserForm userForm) {
        userService.update(principal.getName(), userForm);
        return "redirect:/logout";
    }


    @PreAuthorize("permitAll()")
    @GetMapping("/teams/{teamName}/participant")
    public String getUsersByTeam(Model model, @PathVariable("teamName") String teamName) {
        model.addAttribute("participants", userService.getUsersByTeam(teamName));
        return "list_page/list_part_teams";
    }
}
