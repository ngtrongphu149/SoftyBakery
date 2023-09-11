package com.poly.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.model.Product;
import com.poly.model.ProductImage;

public interface ProductImageDAO extends JpaRepository<ProductImage, Integer> {
	@Query("SELECT pi.imageUrl FROM ProductImage pi WHERE pi.product = ?1")
	List<String> getProductImagesByProductId(Product product);

}
