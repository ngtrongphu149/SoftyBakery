package com.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poly.models.Discount;

@Repository
public interface DiscountDAO extends JpaRepository<Discount, Integer> {
    
}
