package com.example.demo.data;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.model.role;
import com.example.demo.model.user;
import com.example.demo.repo.RoleRepository;
import com.example.demo.repo.UserRepository;


@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Tạo role nếu chưa có
        role userRole = roleRepository.findByName("ROLE_USER")
                .orElseGet(() -> {
                	role r = new role();
                    r.setName("ROLE_USER");
                    return roleRepository.save(r);
                });
        role adminRole = roleRepository.findByName("ROLE_ADMIN")
                .orElseGet(() -> {
                	role r = new role();
                    r.setName("ROLE_ADMIN");
                    return roleRepository.save(r);
                });

        // Tạo user admin nếu chưa có
        if (userRepository.findByUsername("admin").isEmpty()) {
            user admin = new user();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setEnabled(true);
            admin.setRoles(Set.of(adminRole, userRole));
            userRepository.save(admin);
        }
    }
}