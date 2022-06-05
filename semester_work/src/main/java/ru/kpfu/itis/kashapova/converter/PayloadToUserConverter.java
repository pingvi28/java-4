package ru.kpfu.itis.kashapova.converter;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.kashapova.entity.enums.LoginMethod;
import ru.kpfu.itis.kashapova.entity.enums.Role;
import ru.kpfu.itis.kashapova.entity.User;

import java.util.UUID;

@Component
public class PayloadToUserConverter implements Converter<GoogleIdToken.Payload, User> {

    @Override
    public User convert(GoogleIdToken.Payload source) {
        return User.builder()
                .email(String.valueOf(source.get("email")))
                .login(String.valueOf(source.get("name")))
                //.gender("-")
                .way(LoginMethod.OAUTH)
                .password(UUID.randomUUID().toString())
                .role(Role.USER)
                .build();
    }
}
