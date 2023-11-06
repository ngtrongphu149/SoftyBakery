package com.poly.entities;

import lombok.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductImageDTO {
    @JsonProperty("imageId")
    private Integer imageId;
    
    @JsonProperty("productId")
    private int productId;

    @JsonProperty("imageUrl")
    private String imageUrl;
}
