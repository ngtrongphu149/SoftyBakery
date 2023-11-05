package com.poly.entities;

import lombok.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.poly.dto.ProductDTO;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
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
    
    @Column(name = "detail_description")
    private String detailDescription;

    @Column(name = "ingredient")
    private String ingredient;

    @Column(name = "storage_instruction")
    private String storageInstruction;

    @ManyToOne
    @JoinColumn(name = "categoryid")
    private Category category;
    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderDetails;
    
    public Product(ProductDTO pDTO) {
        Product p = pDTO.getProduct();
    	productId = p.getProductId();
    	productName = p.getProductName();
    	description = p.getDescription();
    	price = p.getPrice();
    	category = p.getCategory();
    	orderDetails = p.getOrderDetails();
    }
}
