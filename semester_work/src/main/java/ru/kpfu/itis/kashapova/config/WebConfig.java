package ru.kpfu.itis.kashapova.config;

import java.util.Set;
import javax.servlet.Filter;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.format.FormatterRegistry;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.kpfu.itis.kashapova.filter.EncodingFilter;

@Log
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private Set<GenericConverter> converters;

    @Autowired
    private EncodingFilter encodingFilter;

    @Autowired
    private LocalValidatorFactoryBean validatorFactoryBean;

    @Override
    public Validator getValidator() {
        return validatorFactoryBean;
    }

    @Override
    public MessageCodesResolver getMessageCodesResolver() {
        DefaultMessageCodesResolver codesResolver = new DefaultMessageCodesResolver();
        codesResolver.setMessageCodeFormatter(DefaultMessageCodesResolver.Format.POSTFIX_ERROR_CODE);
        return codesResolver;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        converters.forEach(registry::addConverter);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/bootstrap/**").addResourceLocations("/static/assets/bootstrap/");
        registry.addResourceHandler("/css/**").addResourceLocations("/static/assets/css/");
        registry.addResourceHandler("/fonts/**").addResourceLocations("/static/assets/fonts/");
        registry.addResourceHandler("/images/**").addResourceLocations("/static/assets/imag/");
        registry.addResourceHandler("/js/**").addResourceLocations("/static/js/");
        registry.addResourceHandler("/templates/**").addResourceLocations("/templates/");
    }

    @Bean
    public FilterRegistrationBean<Filter> filterRegistrationBean() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(encodingFilter);
        filterRegistrationBean.addUrlPatterns("/**");
        return filterRegistrationBean;
    }
}
