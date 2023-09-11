package com.poly.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "orderitems")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
	@Id
    @Column(name = "orderitemid")
    private Integer orderItemId;

    @ManyToOne
    @JoinColumn(name = "orderid")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "productid")
    private Product product;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private double price;
    
    public String toString() {
    	return getOrder().getOrderId()+" - "+getProduct().getProductName()+" - "+ getOrderItemId();
    }
}
