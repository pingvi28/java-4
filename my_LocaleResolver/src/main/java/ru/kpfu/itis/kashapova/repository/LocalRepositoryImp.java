package ru.kpfu.itis.kashapova.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.kashapova.entities.Local;

import javax.sql.DataSource;

@Repository
@RequiredArgsConstructor
public class LocalRepositoryImp implements LocalRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("dataSource")
    public void setJdbcTemplate(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Local find(String lang) {
        return jdbcTemplate.queryForObject(
                "select * from local where lang = ?",
                new Object[]{lang},
                new BeanPropertyRowMapper<Local>(Local.class));
    }
}


