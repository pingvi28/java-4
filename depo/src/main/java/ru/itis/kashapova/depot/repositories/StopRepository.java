package ru.itis.kashapova.depot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.kashapova.depot.models.Stop;

public interface StopRepository extends JpaRepository<Stop, Long> {
}
