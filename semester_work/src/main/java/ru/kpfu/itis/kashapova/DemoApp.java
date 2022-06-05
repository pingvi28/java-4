package ru.kpfu.itis.kashapova;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//(exclude = { DataSourceAutoConfiguration.class })
@SpringBootApplication

public class DemoApp {

    public static void main(String[] args) {
        SpringApplication.run(DemoApp.class, args);
    }

    @Bean
    public ExecutorService executorService() {
        return Executors.newCachedThreadPool();
    }

    @Bean
    public JacksonFactory getJacksonFactory() {
        return new JacksonFactory();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
