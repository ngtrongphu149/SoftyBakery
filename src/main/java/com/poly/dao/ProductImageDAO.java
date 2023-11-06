package com.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poly.models.ProductImage;

@Repository
public interface ProductImageDAO extends JpaRepository<ProductImage, Integer> {
    
}
