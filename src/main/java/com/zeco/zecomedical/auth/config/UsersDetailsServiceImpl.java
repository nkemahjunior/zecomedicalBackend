package com.zeco.zecomedical.auth.config;

import com.zeco.zecomedical.model.Users;
import com.zeco.zecomedical.general.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

//@RequiredArgsConstructor
@Service
public class UsersDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private  UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Users> user = usersRepository.findByUsername(username);

        return user.map(UsersDetails::new)
                .orElseThrow( ()-> new UsernameNotFoundException("user not found : " + username));


    }
}
