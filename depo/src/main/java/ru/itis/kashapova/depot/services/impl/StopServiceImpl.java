package ru.itis.kashapova.depot.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.kashapova.depot.dto.NewStopDto;
import ru.itis.kashapova.depot.models.Stop;
import ru.itis.kashapova.depot.repositories.StopRepository;
import ru.itis.kashapova.depot.services.StopService;

@RequiredArgsConstructor
@Service
public class StopServiceImpl implements StopService {
    private final StopRepository stopRepository;

    @Override
    public void addStop(NewStopDto newStopDto) {
        Stop stop = Stop.builder()
                .name(newStopDto.getName())
                .build();

        stopRepository.save(stop);
    }

    @Override
    public void deleteStop(Long id) {
        stopRepository.deleteById(id);
    }
}
