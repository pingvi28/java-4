package ru.kpfu.itis.kashapova.config;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

/**
 * Иногда вам нужно создать многоязычный вебсайт, многоязычный вебсайт помогает вам иметь доступ к большему количеству пользователей. Многоязычный вебсайт так же называется Internationalization (i18n) (Интернационализированный), напротив Localization (L10n) (Локализированный).
 *
 * Этот класс связан с res/mes./mes. (языковой файл)
 */

@Configuration
public class LocalizationConfig {

    @Autowired
    private Environment environment;

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages/messages");
        messageSource.setDefaultEncoding(environment.getProperty("UTF-8"));
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setDefaultLocale(Locale.ENGLISH);
        return messageSource;
    }

    // PasswordValidator
    @Bean
    public LocalValidatorFactoryBean validatorFactoryBean() {
        LocalValidatorFactoryBean factoryBean = new LocalValidatorFactoryBean();
        factoryBean.setValidationMessageSource(messageSource());
        return factoryBean;
    }
}
