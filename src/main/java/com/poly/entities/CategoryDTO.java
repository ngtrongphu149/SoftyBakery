package com.poly.entities;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;

@Data
public class CategoryDTO {
    @JsonProperty("categoryId")
    private Integer categoryId;

    @JsonProperty("categoryName")
    private String categoryName;
    
    @JsonProperty("imageUrl")
    private String imageUrl;
    
    @JsonProperty("description")
    private String description;


}
