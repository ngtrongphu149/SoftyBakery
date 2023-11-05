package com.poly.entities;

import lombok.Data;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "Reviews")
@Data
public class Review {
    @Id
    @Column(name = "reviewid")
    private Integer reviewId;

    @ManyToOne
    @JoinColumn(name = "accountid")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "productid")
    private Product product;

    @Column(name="rating")
    private int rating;

    @Lob
    private String comment;
    
    @Column(name = "reviewdate")
    private Date reviewDate;
}
