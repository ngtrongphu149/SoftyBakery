package com.poly.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;

@Data
public class OrderItemDTO {
	@JsonProperty("orderitemId")
    private Integer orderItemId;

    @ManyToOne
    @JsonProperty("orderid")
    private OrderDTO order;

    @ManyToOne
    @JsonProperty("product")
    private ProductDTO product;

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("price")
    private double price;
}
