package ru.kpfu.itis.kashapova.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.kpfu.itis.kashapova.framework.controllers.BookController;
import ru.kpfu.itis.kashapova.framework.mapper.RequestMapper;
import ru.kpfu.itis.kashapova.framework.mapper.SimpleRequestMapper;

@Configuration
public class Config {
    @Bean
    public BookController bookController() {
        return new BookController();
    }
    @Bean
    public RequestMapper requestMapper() {
        RequestMapper mapper = new SimpleRequestMapper();
        mapper.addRoute("/books", "bookController");
        return mapper;
    }
}
