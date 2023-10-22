package com.poly.models;

import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Orders")
@Data
public class Order {
    @Id
    @Column(name = "orderid")
    private Integer orderId;
    
    @ManyToOne
    @JoinColumn(name = "accountid")
    private Account account;

    @Column(name = "orderdate")
    private LocalDateTime orderDate;

    @Column(name = "totalamount", nullable = false)
    private Double totalAmount;

    @Column(name = "address")
    private String address;

    @Column(name = "status")
    private String status;
}
