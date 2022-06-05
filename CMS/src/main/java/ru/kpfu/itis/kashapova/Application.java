package ru.kpfu.itis.kashapova;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * Main app
 * @SpringBootApplication - для спринга - SpringBoot
 * Нужен для того, чтобы в наше приложении доавлялся http-сервер, для принятия запросов
 * И эти запросы перенаправлял бы приложению
 * */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.run(args);
    }
}
