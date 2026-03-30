package com.example.demo.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.UserRegistrationDTO;
import com.example.demo.model.role;
import com.example.demo.model.user;
import com.example.demo.repo.RoleRepository;
import com.example.demo.repo.UserRepository;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerNewUser(UserRegistrationDTO registrationDto) {
        if (userRepository.findByUsername(registrationDto.getUsername()).isPresent()) {
            throw new RuntimeException("Username đã tồn tại!");
        }
        if (!registrationDto.getPassword().equals(registrationDto.getConfirmPassword())) {
            throw new RuntimeException("Mật khẩu xác nhận không khớp!");
        }

        user user1 = new user();
        user1.setUsername(registrationDto.getUsername());
        user1.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user1.setEnabled(true);

        // Gán role mặc định là ROLE_USER
        role userRole = roleRepository.findByName("ROLE_USER")
                .orElseGet(() -> {
                	role newRole = new role();
                    newRole.setName("ROLE_USER");
                    return roleRepository.save(newRole);
                });
        user1.setRoles(Collections.singleton(userRole));

        userRepository.save(user1);
    }
}