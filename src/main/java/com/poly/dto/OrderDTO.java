package com.poly.dto;

import com.poly.models.Account;
import com.poly.models.Order;

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

