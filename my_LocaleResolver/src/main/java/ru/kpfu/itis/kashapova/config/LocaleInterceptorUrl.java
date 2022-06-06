package ru.kpfu.itis.kashapova.config;

import java.util.Locale;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * preHandle: перехват, когда запрос только что входит, он будет оценен, и потребуется логическое возвращаемое значение. Если он вернет истину, он продолжит выполнение, если он вернет ложь, он не будет выполняться. Обычно используется для проверки входа в систему.
 */

public class LocaleInterceptorUrl extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);

        if (localeResolver == null) {
            throw new IllegalStateException("Can't LocaleResolver found");
        }

        Locale locale = localeResolver.resolveLocale(request);
        localeResolver.setLocale(request, response, locale);
        return true;
    }
}
