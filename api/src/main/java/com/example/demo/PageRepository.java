package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Đánh dấu đây là một Bean Repository để Spring Boot quản lý
public interface PageRepository extends JpaRepository<Pages, Integer> {
    
	// Bạn không cần viết thêm hàm gì ở đây cả!
	// JpaRepository đã cung cấp sẵn các hàm như:
	// - save(Pages p): Thêm mới hoặc cập nhật
	// - findAll(): Lấy tất cả danh sách
	// - findById(Integer id): Tìm theo ID
	// - deleteById(Integer id): Xóa theo ID

}