package com.example.demo.service;

import com.example.demo.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthorUserDetailService implements UserDetailsService {
    @Autowired
    AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        com.example.demo.entites.User user = authRepository.findByEmail(email);
        if (user != null) {
            return new org.springframework.security.core.userdetails.User(user.getEmail(),
                    user.getPassword(), Collections.singleton(new SimpleGrantedAuthority(user.getRole().toString())));
        } else {
            throw new UsernameNotFoundException("Invalid email or password.");
        }

    }


}
