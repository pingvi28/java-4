package ru.kpfu.itis.kashapova.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.kpfu.itis.kashapova.dto.ResumeDto;
import ru.kpfu.itis.kashapova.form.ResumeForm;
import ru.kpfu.itis.kashapova.service.ResumeService;

import java.security.Principal;
import java.util.Optional;

@PreAuthorize("isAuthenticated()")
@Controller
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @GetMapping("/resume/create")
    public String getPageForCreate(Model model, Principal principal) {
        model.addAttribute("user", resumeService.getUserByUserEmail(principal.getName()).get());
        return "createPage/create_resume";
    }

    @PostMapping("/resume/create")
    public String createResume(ResumeForm resumeForm) {
        resumeService.add(resumeForm);
        return "redirect:/resumes";
    }

    @GetMapping("/resume/own")
    public String getMyAd(Model model, Principal principal) {
        model.addAttribute("resumes", resumeService.getByUserEmail(principal.getName()));
        return "list_page/list_resume";
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/resumes")
    public String getResumes(Model model) {
        model.addAttribute("resumes", resumeService.getAll());
        return "list_page/list_resume";
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/resumes/{resumeId}")
    public String getAdPage(@PathVariable("resumeId") Long id, Model model) {
        Optional<ResumeDto> resumeDto = resumeService.getById(id);
        if (resumeDto.isPresent()) {
            model.addAttribute("resume", resumeDto.get());
            return "pages/resume_page";
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ad isn't found");
    }
}
