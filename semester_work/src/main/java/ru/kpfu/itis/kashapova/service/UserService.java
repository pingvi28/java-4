package ru.kpfu.itis.kashapova.service;

import ru.kpfu.itis.kashapova.dto.UserDto;
import ru.kpfu.itis.kashapova.entity.User;
import ru.kpfu.itis.kashapova.entity.UserStatistic;
import ru.kpfu.itis.kashapova.form.SignUpUserForm;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<UserDto> getByLogin(String login);
    Optional<UserDto> getByEmail(String email);
    Optional<UserDto> authenticate(String email, String password);
    void add(SignUpUserForm entity);
    Optional<UserDto> getById(Long id);
    Optional<UserDto> update(String email, SignUpUserForm now);
    void delete(String email);
    List<UserDto> getUsersByTeam(String teamName);
    List<UserDto> getUsersWithActiveAd();
    UserStatistic getUserStatistic(String email);
}