package ru.kpfu.itis.kashapova.service;


import ru.kpfu.itis.kashapova.dto.AdDto;
import ru.kpfu.itis.kashapova.dto.UserDto;
import ru.kpfu.itis.kashapova.form.AdForm;

import java.util.List;
import java.util.Optional;

public interface AdService {

    void add(AdForm adForm);
    List<AdDto> getAllWhereStatusIsActive();
    List<AdDto> getByEmail(String email);
    void setFinished(Long id);
    List<AdDto> getByUserEmail(String name);

    Optional<AdDto> getById(Long id);
    List<AdDto> getByIdRest(Long id);

    Optional<UserDto> getUserByUserEmail(String name);
}
