package com.poly.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.models.Product;
import com.poly.models.ProductImage;

public interface ProductImageDAO extends JpaRepository<ProductImage, Integer> {
}
