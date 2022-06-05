package ru.kpfu.itis.kashapova.service;

import ru.kpfu.itis.kashapova.dto.AdDto;
import ru.kpfu.itis.kashapova.dto.UserDto;
import ru.kpfu.itis.kashapova.form.AdForm;

import java.util.List;
import java.util.Optional;

public interface AdminService {

    List<UserDto> getAllUsers();
    boolean deleteTeam(String name);
    Optional<AdDto> updateAd(Long id, AdForm adForm);
}
