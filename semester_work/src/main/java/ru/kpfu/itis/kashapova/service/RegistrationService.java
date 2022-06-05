package ru.kpfu.itis.kashapova.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.kashapova.entity.User;
import ru.kpfu.itis.kashapova.exception.NotEqualPasswordsException;
import ru.kpfu.itis.kashapova.repository.UserRepository;

@Service
public class RegistrationService {

    @Autowired
    private UserRepository userRepository;

    public void registerUserException(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new DuplicateKeyException("Duplicate key - username field.");
        }
        if (!user.getPassword().equals(user.getPasswordRepeat())) {
            throw new NotEqualPasswordsException("Not equal password");
        }
    }
}
