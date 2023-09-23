package com.poly.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Reviews")
@Data
@NoArgsConstructor
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
    private LocalDateTime reviewDate;
}
