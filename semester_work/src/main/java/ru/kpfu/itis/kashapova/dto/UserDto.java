package ru.kpfu.itis.kashapova.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.kashapova.entity.User;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String email;
    private String login;
    private String gender;
    private Boolean oauthLinked;

    public static UserDto to(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .login(user.getLogin())
                .build();
    }

    public static List<UserDto> to(List<User> users) {
        return users.stream()
                .map(UserDto::to)
                .collect(Collectors.toList());
    }
}
