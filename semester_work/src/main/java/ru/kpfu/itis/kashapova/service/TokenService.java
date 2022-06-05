package ru.kpfu.itis.kashapova.service;

import ru.kpfu.itis.kashapova.form.LoginForm;

public interface TokenService {
    LoginForm authenticate(String token);
}
