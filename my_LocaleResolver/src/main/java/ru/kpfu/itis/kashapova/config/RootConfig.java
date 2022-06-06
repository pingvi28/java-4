package ru.kpfu.itis.kashapova.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import ru.kpfu.itis.kashapova.repository.LocalRepository;
import ru.kpfu.itis.kashapova.repository.LocalRepositoryImp;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@ComponentScan(basePackages = {"ru.kpfu.itis.kashapova.repository", "ru.kpfu.itis.kashapova.entities","ru.kpfu.itis.kashapova.services"})
@PropertySource("classpath:/app.properties")
public class RootConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private Environment env;

    @Bean(name = "dataSource")
    @Primary
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("db.driver")));
        dataSource.setUrl(env.getProperty("db.url"));
        dataSource.setUsername(env.getProperty("db.username"));
        dataSource.setPassword(env.getProperty("db.password"));
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
