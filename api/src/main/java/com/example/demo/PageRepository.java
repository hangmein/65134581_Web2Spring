package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Đánh dấu đây là một Bean Repository để Spring Boot quản lý
public interface PageRepository extends JpaRepository<Pages, Integer> {
    


}