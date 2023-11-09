package com.poly.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poly.models.Product;

public interface ProductDAO extends JpaRepository<Product, Integer> {
	@Query("SELECT pi.imageUrl FROM ProductImage pi WHERE pi.product.productId = ?1")
	List <String> getImageUrlByProductId(int productId);

	@Query("SELECT p FROM Product p WHERE p.category.categoryId = ?1")
    List <Product> getProductByCategory(int categoryId);    
}

