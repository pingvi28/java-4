package ru.kpfu.itis.kashapova.repository;

import org.springframework.stereotype.Repository;
import ru.kpfu.itis.kashapova.entities.Local;

@Repository
public interface LocalRepository {
    Local find(String lang);
}
