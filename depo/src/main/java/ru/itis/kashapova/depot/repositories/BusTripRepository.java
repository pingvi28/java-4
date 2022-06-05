package ru.itis.kashapova.depot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.kashapova.depot.models.BusTrip;

public interface BusTripRepository extends JpaRepository<BusTrip, Long> {
}
