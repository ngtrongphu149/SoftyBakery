package com.poly.entities;

import lombok.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;

@Data
@NoArgsConstructor
public class ProductDTO {
	@JsonProperty("productId")
	private int productId;

    @JsonProperty("productName")
    private String productName;

    @JsonProperty("description")
    private String description;

    @JsonProperty("price")
    private Double price;
    
    @JsonProperty("detailDescription")
    private String detailDescription;

    @JsonProperty("ingredient")
    private String ingredient;

    @JsonProperty("storageInstruction")
    private String storageInstruction;

    @ManyToOne
    @JsonProperty("category")
    private CategoryDTO category;
}
