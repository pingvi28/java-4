package ru.kpfu.itis.kashapova.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.kashapova.validation.Password;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpUserForm {

    @Pattern(regexp = "[a-zA-Z0-9\\\\._-]{3,}", message = "неверный формат, минимум 3 символа")
    private String login;

    @NotEmpty(message = "эта ячейка не должена быть пустой")
    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "Wrong format of email")
    private String email;

    @NotEmpty(message = "пароль не должен быть пустым")
    private String password;

    private String passwordRepeat;
}
