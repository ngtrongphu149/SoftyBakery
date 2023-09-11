package com.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.model.Review;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewDAO extends JpaRepository<Review, Integer> {
    // Các phương thức truy vấn dữ liệu liên quan đến bảng Reviews
}


