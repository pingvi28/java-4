package ru.kpfu.itis.kashapova.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import ru.kpfu.itis.kashapova.entity.Team;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamDto {

    private String name;
    private UserDto creator;
    private List<UserDto> participant;

    public static TeamDto to(Team team) {
        return TeamDto.builder().creator(UserDto.to(team.getCreator())).name(team.getName()).build();
    }
}
