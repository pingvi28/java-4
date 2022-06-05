package ru.kpfu.itis.kashapova.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.kpfu.itis.kashapova.dto.AdDto;
import ru.kpfu.itis.kashapova.form.AdForm;
import ru.kpfu.itis.kashapova.service.AdService;

import java.security.Principal;
import java.util.Optional;

@Controller
@PreAuthorize("isAuthenticated()")
public class AdController {

    @Autowired
    private AdService adService;

    @PreAuthorize("permitAll()")
    @GetMapping("/ads")
    public String getAds(Model model) {
        model.addAttribute("ads", adService.getAllWhereStatusIsActive());
        return "list_page/list_ad";
    }

    @GetMapping("/ad/own")
    public String getMyAd(Model model, Principal principal) {
        model.addAttribute("ads", adService.getByEmail(principal.getName()));
        return "list_page/list_ad";
    }

    @GetMapping("/ad/create")
    public String getPageForCreate(Model model, Principal principal) {
        model.addAttribute("user", adService.getUserByUserEmail(principal.getName()).get());
        return "createPage/create_ad";
    }

    @PostMapping("/ad/create")
    public String createResume(AdForm adForm) {
        adService.add(adForm);
        return "redirect:/ads";
    }

    @PreAuthorize("permitAll()")
    @ResponseBody
    @GetMapping("/ads/{adId}")
    public ResponseEntity<AdDto> getAdPage(@PathVariable("adId") Long id) {
        Optional<AdDto> adDto = adService.getById(id);
        if (adDto.isPresent()) {
            return ResponseEntity.ok(adDto.get());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ad isn't found");
    }
}
