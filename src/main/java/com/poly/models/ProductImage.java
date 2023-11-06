package com.poly.models;

import jakarta.persistence.*;

import lombok.Data;

@Entity
@Table(name = "productimages")
@Data
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "imageid")
    private int imageId;

    @ManyToOne
    @JoinColumn(name = "productid")
    private Product product;

    @Column(name = "imageurl")
    private String imageURL;
}
