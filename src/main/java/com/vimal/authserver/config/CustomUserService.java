package com.vimal.authserver.config;

import com.vimal.authserver.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserService implements UserDetailsService {
    @Autowired
    private AccountRepository repository;

    @Autowired
    PasswordEncoder encoder;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
       return repository.findByUserName(userName).map(u->
           new User(u.getUserName(),
                   encoder.encode(u.getPassword()),
                   true,true,true,true,
                   AuthorityUtils.createAuthorityList("write","read")))
               .orElseThrow(()-> new UsernameNotFoundException("User name Doesn't Exists"));
    }
}
