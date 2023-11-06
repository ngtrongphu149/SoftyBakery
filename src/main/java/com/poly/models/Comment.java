package com.poly.models;

import jakarta.persistence.*;

import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "Comments")
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commentid")
    private int commentId;

    @ManyToOne
    @JoinColumn(name = "username")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "productid")
    private Product product;

    @Column(name = "rating")
    private int rating;

    @Column(name = "comment")
    private String comment;

    @Column(name = "commentdate")
    private Date commentDate;

    @ManyToOne
    @JoinColumn(name = "parentcommentid")
    private Comment parentComment;
}
