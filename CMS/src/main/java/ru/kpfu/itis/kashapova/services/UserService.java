package ru.kpfu.itis.kashapova.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.kashapova.dao.UserRepository;
import ru.kpfu.itis.kashapova.models.UserDetailsAdapter;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return new UserDetailsAdapter(userRepository.findByEmail(email).
                orElseThrow(() -> new UsernameNotFoundException("Not found")));
    }
}
