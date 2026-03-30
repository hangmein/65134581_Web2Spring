package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.user;

import java.util.Optional;

public interface UserRepository extends JpaRepository<user, Long> {
    Optional<user> findByUsername(String username);
}