package ru.kpfu.itis.kashapova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.kashapova.entity.Resume;

import java.util.List;

public interface ResumeRepository extends JpaRepository<Resume, Long> {

    List<Resume> getAllByOwnerEmail(String login);

}
