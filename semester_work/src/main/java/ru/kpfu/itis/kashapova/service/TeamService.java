package ru.kpfu.itis.kashapova.service;

import ru.kpfu.itis.kashapova.dto.TeamDto;
import ru.kpfu.itis.kashapova.dto.UserDto;
import ru.kpfu.itis.kashapova.form.TeamForm;

import java.util.List;
import java.util.Optional;

public interface TeamService {
    void createTeam(TeamForm teamForm);

    List<TeamDto> getTeamsByCreatorEmail(String email);

    List<TeamDto> getTeamsByParticipant(String name);

    Optional<UserDto> getCreator(String email);

    Optional<TeamDto> getTeamByName(String name);

    List<TeamDto> getAll();

    List<TeamDto> getTeamsByUser(String login);
}
