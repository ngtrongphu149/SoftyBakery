package com.poly.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.models.Product;
import com.poly.models.ProductImage;

public interface ProductImageDAO extends JpaRepository<ProductImage, Integer> {
	@Query("SELECT pi.imageUrl FROM ProductImage pi WHERE pi.product.productId = ?1")
	List<String> getProductImagesByProductId(int productId);
	
	@Query("SELECT pi FROM ProductImage pi WHERE pi.product = ?1")
	List<ProductImage> getPIByProductId(Product product);
	
	@Query("SELECT p.imageId FROM ProductImage p ORDER BY p.imageId DESC LIMIT 1")
    Integer findTopImageId();
}
