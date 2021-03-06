package ru.kpfu.itis.kashapova.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.kashapova.services.LocalService;


@Controller
public class MainController {

    private MessageSourceAccessor msa;
    private LocalService localService;

    @Autowired
    private void setMessageSourceAccessor(MessageSource ms){
        this.msa = new MessageSourceAccessor(ms);
    }

    @RequestMapping("/")
    public String index(ModelMap map) {
        return "index";
    }

    @RequestMapping(value = "/{locale:en|fr|ru}/login")
    public String login(Model model, @PathVariable String locale) {
        return "login";
    }

    @RequestMapping(value = "/{locale:EN|RU}/login2")
    public String login2(Model model, @PathVariable String locale) {
        model.addAttribute("local", localService.findLocalByLanguage(locale));
        return "login2";
    }
}
