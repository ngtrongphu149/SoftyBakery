package com.poly.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.models.Review;


public interface ReviewDAO extends JpaRepository<Review, Integer> {
    @Query("SELECT r FROM Review r WHERE r.product.productId = :productId") 
    List<Review> getByProductId(int productId);
    
    @Query("SELECT AVG(r.rating) FROM Review r " +
    "WHERE r.product.id = :productId " +
    "GROUP BY r.product.id")
    Double getAvgRating(int productId);
}


