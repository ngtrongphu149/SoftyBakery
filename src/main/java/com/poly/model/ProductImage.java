package com.poly.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "productimages")

public class ProductImage {
    @Id
    @Column(name = "imageid")
    private Integer imageId;

    @ManyToOne
    @JoinColumn(name = "productid")
    private Product product;

    @Column(nullable = false, name = "imageurl")
    private String imageUrl;
}
