package com.poly.entities;

import java.util.Date;

import com.poly.model.Account;
import com.poly.model.Order;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO extends Order{
    private Account account;
    public OrderDTO(Order order) {
    	super.setOrderId(order.getOrderId());
    	super.setOrderDate(order.getOrderDate());
    	super.setTotalAmount(order.getTotalAmount());
    	super.setAddress(order.getAddress());
    	super.setStatus(order.getStatus());
	}
}

