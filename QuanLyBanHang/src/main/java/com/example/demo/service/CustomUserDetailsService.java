package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.user;
import com.example.demo.repo.UserRepository;
import com.example.demo.model.role;
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        user user1 = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        // Chuyển đổi từ User entity sang UserDetails của Spring Security
        return org.springframework.security.core.userdetails.User
                .withUsername(user1.getUsername())
                .password(user1.getPassword())
                .roles(user1.getRoles().stream().map(role -> role.getName().replace("ROLE_", "")).toArray(String[]::new))
                .disabled(!user1.isEnabled())
                .build();
    }
}