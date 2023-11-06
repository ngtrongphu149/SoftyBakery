package com.poly.models;

import jakarta.persistence.*;

import lombok.Data;

@Entity
@Table(name = "specialoptions")
@Data
public class SpecialOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "specialoptionid")
    private int specialOptionId;

    @ManyToOne
    @JoinColumn(name = "productid")
    private Product product;

    @Column(name = "option")
    private String option;
}
