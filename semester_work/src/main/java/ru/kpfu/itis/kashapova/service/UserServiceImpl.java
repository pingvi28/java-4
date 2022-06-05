package ru.kpfu.itis.kashapova.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.kashapova.dto.UserDto;
import ru.kpfu.itis.kashapova.entity.UserStatistic;
import ru.kpfu.itis.kashapova.entity.enums.LoginMethod;
import ru.kpfu.itis.kashapova.entity.enums.Role;
import ru.kpfu.itis.kashapova.entity.User;
import ru.kpfu.itis.kashapova.exception.DuplicateLoginException;
import ru.kpfu.itis.kashapova.exception.NotEqualPasswordsException;
import ru.kpfu.itis.kashapova.exception.NotFoundException;
import ru.kpfu.itis.kashapova.form.SignUpUserForm;
import ru.kpfu.itis.kashapova.repository.UserRepository;
import ru.kpfu.itis.kashapova.repository.UserStatisticRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserStatisticRepository userStatisticRepository;
    private final PasswordEncoder passwordEncoder;

    private final Class<User> targetType = User.class;
    @Autowired
    private ConversionService conversionService;
    @Autowired
    private RegistrationService registerService;

    public UserServiceImpl(UserRepository userRepository, UserStatisticRepository userStatisticRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userStatisticRepository = userStatisticRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<UserDto> getByLogin(String login) {
        return userRepository.getByLogin(login).map(UserDto::to);
    }

    @Override
    public Optional<UserDto> getByEmail(String email) {
        return userRepository.getByEmail(email).map(UserDto::to);
    }

    @Override
    public Optional<UserDto> authenticate(String email, String password) {
        Optional<User> user = userRepository.getByEmail(email);
        if (user.isEmpty()) throw new NotFoundException("can't find");
        if (passwordEncoder.matches(password, user.get().getPassword())) {
            return user.map(UserDto::to);
        }
        else throw new NotEqualPasswordsException("");
        //return Optional.empty();
    }

    @Override
    public Optional<UserDto> update(String email, SignUpUserForm now) {
        Optional<User> user = userRepository.getByEmail(email);
        if (user.isPresent()) {
            User nowUser = conversionService.convert(now, targetType);
            assert nowUser != null;
            nowUser.setPassword(passwordEncoder.encode(now.getPassword()));
            userRepository.delete(user.get());
            userRepository.save(nowUser);
            return Optional.of(UserDto.to(nowUser));
        }
        else throw new UsernameNotFoundException("User isn't found");
    }

    @Override
    public void delete(String email) {
        userRepository.getByEmail(email).ifPresent(userRepository::delete);
    }

    @Override
    public void add(SignUpUserForm entity) throws DuplicateKeyException, NotEqualPasswordsException {
        User user = conversionService.convert(entity, targetType);
        assert user != null;
        try {
            registerService.registerUserException(user);
        } catch (NotEqualPasswordsException ignored) {
            throw new NotEqualPasswordsException();
        }catch (DuplicateKeyException ignored) {
            throw new DuplicateKeyException("");
        }
        user.setRole(Role.USER);
        user.setWay(LoginMethod.REGISTRATION);
        user.setPassword(passwordEncoder.encode(entity.getPassword()));

        try{
            userRepository.save(user);
        }catch (Exception ignored) {
            throw new DuplicateLoginException("I find such user login");
        }
    }

    @Override
    public Optional<UserDto> getById(Long id) {
        return userRepository.getById(id).map(UserDto::to);
    }

    @Override
    public List<UserDto> getUsersByTeam(String teamName) {
        return userRepository.getUsersByTeamName(teamName)
                .stream().map(UserDto::to).collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getUsersWithActiveAd() {
        return userRepository.getUserWithActiveAd()
                .stream()
                .map(UserDto::to)
                .collect(Collectors.toList());
    }

    @Override
    public UserStatistic getUserStatistic(String email) {
        return userStatisticRepository.getUserStatisticWithParticipantTeam(email);
    }
}






