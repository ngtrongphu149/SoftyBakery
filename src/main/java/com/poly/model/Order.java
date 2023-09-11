package com.poly.model;

import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Orders")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    public String toString() {
        return orderId + " - " + (account != null ? account.getFullName() : "N/A") + " - " + orderDate + " - " + totalAmount + " - " + address + " - " + status;
    }
}
