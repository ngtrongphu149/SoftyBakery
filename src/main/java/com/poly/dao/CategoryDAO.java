package com.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.poly.models.Category;

@Repository
public interface CategoryDAO extends JpaRepository<Category, Integer> {
    @Query("SELECT c FROM Category c where c.categoryId = ?1")
    Category fineById(int productId);
}
