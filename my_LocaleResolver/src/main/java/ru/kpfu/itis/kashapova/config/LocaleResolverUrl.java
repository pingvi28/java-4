package ru.kpfu.itis.kashapova.config;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.LocaleResolver;

public class LocaleResolverUrl implements LocaleResolver {

    private static final String ATTRIBUTE_NAME = "url_local";

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String uri = request.getRequestURI();
        Locale locale = null;
        // English
        if (uri.startsWith(request.getServletContext().getContextPath() + "/en/")) {
            locale = Locale.ENGLISH;
        }
        // French
        else if (uri.startsWith(request.getServletContext().getContextPath() + "/fr/")) {
            locale = Locale.FRANCE;
        }
        // Rus
        if (uri.startsWith(request.getServletContext().getContextPath() + "/ru/")) {
            locale = new Locale("ru", "RU");;
        }

        if (locale != null) {
            request.getSession().setAttribute(ATTRIBUTE_NAME, locale);
        }
        else {
            locale = (Locale) request.getSession().getAttribute(ATTRIBUTE_NAME);
            if (locale == null) {
                locale = Locale.ENGLISH;
            }
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {}
}
