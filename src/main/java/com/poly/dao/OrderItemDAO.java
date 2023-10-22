package com.poly.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.models.OrderItem;

public interface OrderItemDAO extends JpaRepository<OrderItem, Integer> {
	@Query("SELECT oi.orderItemId FROM OrderItem oi ORDER BY oi.orderItemId DESC LIMIT 1")
    Integer findTopOrderItemId();
	
	@Query("SELECT oi FROM OrderItem oi WHERE oi.order.orderId = ?1")
	List<OrderItem> getOrderItemByOrderId(int orderId);
}

