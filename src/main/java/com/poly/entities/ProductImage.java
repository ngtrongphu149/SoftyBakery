package com.poly.entities;

import lombok.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.poly.dto.ProductDTO;

import jakarta.persistence.*;


@Entity
@Table(name = "productimages")
@Data
@NoArgsConstructor
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
