package com.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poly.models.Review;

@Repository
public interface ReviewDAO extends JpaRepository<Review, Integer> {

}