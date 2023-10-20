package com.poly.models;

import lombok.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.poly.dto.ProductDTO;

import jakarta.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {
	@Id
	@Column(name = "productid")
	private int productId;

    @Column(name = "productname", nullable = false)
    private String productName;

    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false)
    private Double price;
    
    
    @ManyToOne
    @JoinColumn(name = "categoryid")
    private Category category;
    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderDetails;
    
    public Product(ProductDTO p) {
    	productId = p.getProductId();
    	productName = p.getProductName();
    	description = p.getDescription();
    	price = p.getPrice();
    	category = p.getCategory();
    	orderDetails = p.getOrderDetails();
    }
    public String toString() {
    	return productId + " - " + productName + " - " +description+ " - " +price+ " - " +category.getCategoryId();
    }
}
