package com.poly.model;

import lombok.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.poly.entities.ProductDTO;

import jakarta.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "productimages")

public class ProductImage {
    @Id
    @Column(name = "imageid")
    private Integer imageId;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "productid")
    private Product product;

    @Column(nullable = false, name = "imageurl")
    private String imageUrl;
    
    public ProductImage(ProductDTO p) {
    	imageId = null;
    	product = p.getProduct();
    	imageUrl = p.getImageUrl();
    }
}
