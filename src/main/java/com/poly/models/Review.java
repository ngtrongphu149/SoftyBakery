package com.poly.models;

import jakarta.persistence.*;

import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "reviews")
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reviewid")
    private int reviewId;

    @Column(name = "username")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "productid")
    private Product product;

    @Column(name = "rating")
    private int rating;

    @Column(name = "comment")
    private String comment;

    @Column(name = "reviewdate")
    private Date reviewDate;
}
