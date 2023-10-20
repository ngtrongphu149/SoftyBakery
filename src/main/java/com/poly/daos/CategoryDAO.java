package com.poly.daos;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.models.Category;
import com.poly.models.Product;

import jakarta.persistence.TypedQuery;

public interface CategoryDAO extends JpaRepository<Category, Integer> {
    @Query("SELECT p FROM Category p where p.categoryId = ?1")
    Category findById2(int productId);
}

