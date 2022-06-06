package ru.kpfu.itis.kashapova.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;
import ru.kpfu.itis.kashapova.exceptions.ServerErrorException;
import ru.kpfu.itis.kashapova.models.Article;
import ru.kpfu.itis.kashapova.models.User;
import ru.kpfu.itis.kashapova.services.ArticleService;

import javax.validation.Valid;

/**
 * @RequestMapping  задает адрес, по которму будет доступен контролер
 * Используется @PreAuthorize("hasAuthority('ADMIN')") - настройка доступа
 * */
@Controller
@RequestMapping("/")
public class ArticlesController {
    @Autowired
    private ArticleService articleService;

    @GetMapping
    public String index(ModelMap map, Authentication authentication) {
        map.put("articles", articleService.getArticlesWithPermissions(authentication));
        return "articles/index";
    }

    @GetMapping("{slug}")
    public String slug(@PathVariable String slug, ModelMap map, Authentication authentication) {
        Article article = articleService.findBySlug(slug, authentication);
        map.put("article", article);
        return "articles/article";
    }

    @GetMapping("create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String createIndex(ModelMap map) {
        map.put("article", new Article());
        return "articles/create";
    }

    @PostMapping("create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String create(
            RedirectAttributes redirectAttributes,
            @ModelAttribute("article") Article article,
            @ModelAttribute("user") User user,
            @RequestParam(defaultValue = "false") boolean checkbox,
            BindingResult result,
            ModelMap map
    ) {
        try {
            redirectAttributes.addFlashAttribute("message", "Статья сохранена");
            articleService.createArticle(article,checkbox);
        }catch (IllegalArgumentException ex) {
            throw new ServerErrorException("IllegalArgumentException: article can't be created");
        }
        return "redirect:" + UriComponentsBuilder.fromPath("/create").build();
    }

    @GetMapping("edit/{slug}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String editIndex(@PathVariable String slug, ModelMap map, Authentication authentication) {
        Article article = articleService.findBySlug(slug, authentication);
        map.put("article", article);
        return "articles/edit";
    }

    @PostMapping("edit/{slug}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String edit(
            RedirectAttributes redirectAttributes,
            @ModelAttribute("article") Article article,
            BindingResult result,
            ModelMap map
    ) {
        redirectAttributes.addFlashAttribute("message", "Статья успешно обновлена");
        articleService.updateArticle(article);
        return "redirect:" + UriComponentsBuilder.fromPath("/edit/{slug}").build();
    }

    @GetMapping("delete/{slug}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String delete(@PathVariable String slug, ModelMap map, Authentication authentication) {
        articleService.deleteArticle(slug);
        return "articles/index";
    }
}
