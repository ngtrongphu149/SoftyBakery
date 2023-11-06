package com.poly.entities;

import lombok.Data;

import jakarta.persistence.*;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class Review {
    @JsonProperty("reviewId")
    private Integer reviewId;

    @JsonProperty("account")
    private AccountDTO account;

    @JsonProperty("productId")
    private ProductDTO product;

    @JsonProperty("rating")
    private int rating;

    @JsonProperty("comment")
    private String comment;
    
    @JsonProperty("reviewDate")
    private Date reviewDate;
}
