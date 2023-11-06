package com.poly.models;

import jakarta.persistence.*;

import lombok.Data;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderid")
    private int orderId;

    @ManyToOne
    @JoinColumn(name = "username")
    private Account account;

    @Column(name = "message")
    private String message;

    @Column(name = "orderdate")
    private Date orderDate;

    @ManyToOne
    @JoinColumn(name = "discount")
    private Discount discount;

    @Column(name = "address")
    private String address;

    @Column(name = "status")
    private String status;

    @OneToMany
    @JoinColumn(name = "orders")
    List<OrderItem> orderItems;
}
