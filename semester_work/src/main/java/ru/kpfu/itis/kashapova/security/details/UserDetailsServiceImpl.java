package ru.kpfu.itis.kashapova.security.details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.kashapova.repository.UserRepository;;

/**
 *  interface with 1 method UserDetails loadUserByUsername(String var1) throws UsernameNotFoundException;
 *  Позволяет получить из источника данных объект пользователя и сформировать из него объект UserDetails который будет использоваться контекстом Spring Security.
 */
@Service("myUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // поиск по почте
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return  new UserDetailsImpl(userRepository
                .getByEmail(s).orElseThrow(() ->
                        new UsernameNotFoundException("User with this email wasn't found")));
    }
}
