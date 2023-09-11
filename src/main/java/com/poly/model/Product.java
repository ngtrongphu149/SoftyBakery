package com.poly.model;

import lombok.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    
    public String toString() {
    	return productId + " - " + productName + " - " +description+ " - " +price+ " - " +category.getCategoryId();
    }
}
