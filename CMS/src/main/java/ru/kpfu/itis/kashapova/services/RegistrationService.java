package ru.kpfu.itis.kashapova.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.kashapova.repository.UserRepository;
import ru.kpfu.itis.kashapova.exceptions.PasswordsDoNotMatchException;
import ru.kpfu.itis.kashapova.models.Role;
import ru.kpfu.itis.kashapova.models.User;

@Service
public class RegistrationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new DuplicateKeyException("Duplicate key - username field.");
        }
        if (!user.getPassword().equals(user.getPasswordRepeat())) {
            throw new PasswordsDoNotMatchException("Not equal password");
        }
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
