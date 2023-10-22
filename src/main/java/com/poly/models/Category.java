package com.poly.models;

import lombok.Data;

import jakarta.persistence.*;

@Entity
@Table(name = "Categories")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoryid")
    private Integer categoryId;

    @Column(name = "categoryname", nullable = false)
    private String categoryName;
    
    @Column(name = "imageurl", nullable = false)
    private String imageUrl;
    
    @Column(name = "description", nullable = false)
    private String description;


}
