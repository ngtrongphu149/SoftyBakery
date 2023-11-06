package com.poly.entities;

import lombok.*;
import jakarta.persistence.*;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class OrderDTO {
    @JsonProperty("orderId")
    private Integer orderId;
    
    @ManyToOne
    @JsonProperty("account")
    private AccountDTO account;

    @JsonProperty("orderDate")
    private Date orderDate;

    @JsonProperty("totalAmount")
    private Double totalAmount;

    @JsonProperty("address")
    private String address;

    @JsonProperty("status")
    private String status;
}
