package ru.kpfu.itis.kashapova.services;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.kashapova.exceptions.AccessDeniedException;
import ru.kpfu.itis.kashapova.models.User;
import ru.kpfu.itis.kashapova.repository.ArticleRepository;
import ru.kpfu.itis.kashapova.exceptions.NotFoundArticleException;
import ru.kpfu.itis.kashapova.models.Article;
import ru.kpfu.itis.kashapova.models.Role;

import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class ArticleService {

    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");
    private static final String USER_ID = "user";

    @Autowired
    private ArticleRepository articleRepository;

    public Iterable<Article> getArticlesWithPermissions(Authentication authentication) {
        Role roleAccess = Role.USER;
        if (authentication != null) {
            String userRole = authentication.getAuthorities().stream().findFirst().get().toString();
            if (userRole.equals(Role.ADMIN.getDescription())) {
                return articleRepository.findAll();
            }
        }
        return articleRepository.findAllByRole(roleAccess);
    }

    public void createArticle(Article article, Boolean check) {
        if(check){
            article.setRole(Role.ADMIN);
        }else{
            article.setRole(Role.USER);
        }

        String slug = makeSlug(article.getName());

        Optional<Article> articleTemplate = articleRepository.findTopByOrderByIdDesc();
        if (articleTemplate.isPresent()) {
            slug = USER_ID + (articleTemplate.get().getId() + 1) + slug;
        } else {
            slug = USER_ID + 1 + slug;
        }

        article.setSlug(slug);
        article.setTitle(removeTags(article.getName()));
        article.setContent(cleanupFromJs(article.getContent()));
        article.setCreatingTime(setData());
        articleRepository.save(article);
    }

    public Article findBySlug(String slug, Authentication authentication) {
        Article articleUnwrapped = articleRepository.findBySlug(slug)
                .orElseThrow(() -> new NotFoundArticleException(slug));

        if (articleUnwrapped.getRole() == Role.ADMIN) {
            if (authentication != null) {
                String userRole = authentication.getAuthorities().stream().findFirst().get().toString();
                if (!userRole.equals(Role.ADMIN.getDescription())) {
                    throw new AccessDeniedException("Haven't permissions");
                }
            } else {
                throw new AccessDeniedException(("Haven't permissions"));
            }
        }
        return articleUnwrapped;
    }

    public void updateArticle(Article article) {
        Optional<Article> articleOptional = articleRepository.findBySlug(article.getSlug());
        if (!articleOptional.isPresent()) {
            return;
        }
        Article updateArticle = articleOptional.get();
        updateArticle.setName(article.getName());
        updateArticle.setContent(article.getContent());
        updateArticle.setCreatingTime(setData());
        articleRepository.save(updateArticle);
    }

    public void deleteArticle(String slug) {
        Optional<Article> articleWrapped = null;
        try {
            articleWrapped = articleRepository.findBySlug(slug);
        } catch (NotFoundArticleException ex){}
        
        if (!articleWrapped.isPresent()) {
            return;
        }
        articleRepository.delete(articleWrapped.get());
    }

    private String makeSlug(String input) {
        String nonwhitespace = WHITESPACE.matcher(input).replaceAll(":");
        String normalized = Normalizer.normalize(nonwhitespace, Normalizer.Form.NFD);
        normalized = removeTags(normalized);
        return normalized.toLowerCase(Locale.ENGLISH);
    }

    public String setData(){
        Locale.setDefault(new Locale("ru"));
        String date = new SimpleDateFormat().format(new Date());
        return date = date.replace('.', '/');
    }

    private String removeTags(String string) {
        return string.replaceAll("\\<[^>]*>", "");
    }

    private String cleanupFromJs(String string) {
        return Jsoup.clean(string, Safelist.basic());
    }
}
