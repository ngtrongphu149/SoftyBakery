package com.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.models.Coupon;

public interface CouponDAO extends JpaRepository<Coupon,Integer> {
    
}
