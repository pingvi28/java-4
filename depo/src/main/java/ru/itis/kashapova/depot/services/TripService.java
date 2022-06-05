package ru.itis.kashapova.depot.services;

import ru.itis.kashapova.depot.dto.NewStopDto;
import ru.itis.kashapova.depot.models.BusTrip;

public interface TripService {
    void addTrip(NewStopDto newStopDto);
    void deleteTrip(Long id);
    void setState(BusTrip.State state, Long tripId);
}
