package com.poly.dto;

import com.poly.entities.Product;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDTO{
	private Product product;
	private String imageUrl = "";
	private int quantity = 1;
}
