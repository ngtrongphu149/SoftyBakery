package com.poly.entities;

import com.poly.model.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDTO extends Product {
	public ProductDTO(Product prd) {
		super.setProductId(prd.getProductId());
		super.setProductName(prd.getProductName());
		super.setDescription(prd.getDescription());
		super.setPrice(prd.getPrice());
		super.setCategory(prd.getCategory());
	}
	private String imageUrl = "";
	private int quantity = 1;
	
	public Product getProduct() {
        Product product = new Product();
        product.setProductId(getProductId());
        product.setProductName(getProductName());
        product.setDescription(getDescription());
        product.setPrice(getPrice());
        product.setCategory(getCategory());
        return product;
    }
	
	public String toString() {
		return getProductId() + " - " + getProductName() + " - " + imageUrl + " - " + quantity;
	}
}
