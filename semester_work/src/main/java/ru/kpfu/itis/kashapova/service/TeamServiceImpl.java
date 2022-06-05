package ru.kpfu.itis.kashapova.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.kashapova.dto.TeamDto;
import ru.kpfu.itis.kashapova.dto.UserDto;
import ru.kpfu.itis.kashapova.entity.Team;
import ru.kpfu.itis.kashapova.entity.User;
import ru.kpfu.itis.kashapova.form.TeamForm;
import ru.kpfu.itis.kashapova.repository.TeamRepository;
import ru.kpfu.itis.kashapova.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConversionService conversionService;

    private final Class<Team> targetType = Team.class;

    @Override
    public void createTeam(TeamForm teamForm) {
        Team team = conversionService.convert(teamForm, targetType);
        User user = userRepository.getByLogin(teamForm.getCreatorLogin())
                .orElseThrow(() -> new UsernameNotFoundException("user with login " + teamForm.getCreatorLogin() + " isn't found"));
        assert team != null;
        team.setCreator(user);
        teamRepository.save(team);
    }

    @Override
    public List<TeamDto> getTeamsByCreatorEmail(String email) {
        return teamRepository.getTeamByCreatorEmail(email)
                .stream().map(TeamDto::to).collect(Collectors.toList());
    }

    @Override
    public List<TeamDto> getTeamsByParticipant(String email) {
        return teamRepository.getTeamByParticipantEmail(email)
                .stream().map(TeamDto::to).collect(Collectors.toList());
    }

    @Override
    public Optional<UserDto> getCreator(String email) {
        return userRepository.getByEmail(email).map(UserDto::to);
    }

    @Override
    public Optional<TeamDto> getTeamByName(String name) {
        return teamRepository.findByName(name).map(team -> {
            TeamDto teamDto = TeamDto.to(team);
            teamDto.setParticipant(team.getParticipant().stream().map(UserDto::to).collect(Collectors.toList()));
            return teamDto;
        });
    }

    @Override
    public List<TeamDto> getAll() {
        return teamRepository.findAll().stream().map(TeamDto::to).collect(Collectors.toList());
    }

    @Override
    public List<TeamDto> getTeamsByUser(String login) {
        return teamRepository.getTeamByParticipantLogin(login)
                .stream().map(TeamDto::to).collect(Collectors.toList());
    }

}
