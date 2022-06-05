package ru.itis.kashapova.depot.services;

import ru.itis.kashapova.depot.dto.NewStopDto;

public interface StopService {
    void addStop(NewStopDto newStopDto);
    void deleteStop(Long id);
}
