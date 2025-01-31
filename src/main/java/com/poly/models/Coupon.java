package com.poly.models;

import lombok.Data;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.poly.dto.DiscountType;

@Entity
@Table(name = "coupons")
@Data
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "couponid")
    private int couponId;

    @Column(name = "couponcode")
    private String couponCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "discounttype")
    private DiscountType discountType;

    @Column(name = "discountvalue")
    private double discountValue;

    @Column(name = "minpurchaseamount")
    private Double minPurchaseAmount;

    @Column(name = "maxusage")
    private Integer maxUsage;

    @Column(name = "startdate")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime startDate;

    @Column(name = "enddate")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime endDate;

    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    @OneToMany(mappedBy = "coupon")
    private List<Order> order;

    @Column(name = "isactive")
    private boolean isActive;
}
