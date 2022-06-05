package ru.kpfu.itis.kashapova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.kashapova.entity.Ad;

import java.util.List;

public interface AdRepository extends JpaRepository<Ad, Long> {

    List<Ad> getAllByOwnerEmail(String email);
}
