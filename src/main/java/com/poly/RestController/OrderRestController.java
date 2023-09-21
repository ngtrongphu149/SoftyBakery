package com.poly.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.poly.dao.*;
import com.poly.entities.*;
import com.poly.model.Account;
import com.poly.model.Order;
import com.poly.model.OrderItem;

import DB.UserUtils;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/rest/order")
public class OrderRestController {
	@Autowired
	CategoryDAO cDAO;
	@Autowired
	ProductDAO pDAO;
	@Autowired
	ProductImageDAO piDAO;
	@Autowired
	OrderDAO oDAO;
	@Autowired
	OrderItemDAO oiDAO;
	@Autowired
	AccountDAO aDAO;
	
	
    @GetMapping
    public ResponseEntity<List<OrderDTO>> findAll() {
    	List<OrderDTO> orders = new ArrayList<>();
    	for (Order o : oDAO.findAll()) {
		    OrderDTO oDTO = new OrderDTO(o);
		    oDTO.setAccount(o.getAccount());
		    orders.add(oDTO);
		}
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Integer id) {
        Optional<Order> optionalOrder = oDAO.findById(id);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        if (order.getOrderId() != null) {
            return ResponseEntity.badRequest().build();
        }        
        Order createdOrder = oDAO.save(order);
        return ResponseEntity.ok(createdOrder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Integer id, @RequestBody Order order) {
        if (!oDAO.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        order.setOrderId(id);
        Order updatedOrder = oDAO.save(order);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer id) {
        if (!oDAO.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        oDAO.deleteById(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/{id}/updateStatus")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Integer id) {
        Optional<Order> optionalOrder = oDAO.findById(id);
        if (!optionalOrder.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        Order order = optionalOrder.get();
        String currentStatus = order.getStatus();
        OrderStatus newStatus;
        
        if (OrderStatus.DANG_CHO.getValue().equals(currentStatus)) {
            newStatus = OrderStatus.DA_GIAO;
        } else if (OrderStatus.DA_GIAO.getValue().equals(currentStatus)) {
            newStatus = OrderStatus.DA_HUY;
        } else if (OrderStatus.DA_HUY.getValue().equals(currentStatus)) {
            newStatus = OrderStatus.DANG_CHO;
        } else {
            return ResponseEntity.badRequest().build();
        }
        
        order.setStatus(newStatus.getValue());
        Order updatedOrder = oDAO.save(order);
        return ResponseEntity.ok(updatedOrder);
    }
    @GetMapping("/detail/{orderId}")
    public ResponseEntity<List<OrderItem>> getOrderItemByOrderId(@PathVariable("orderId") int orderId) {
    		List<OrderItem> oiList = new ArrayList<>(); 
    		for(OrderItem oi : oiDAO.getOrderItemByOrderId(orderId)) {
    			ProductDTO pDTO = new ProductDTO(oi.getProduct());
    		    
    		    try {
    		        List<String> imageUrlList = pDAO.getImageUrlByProductId(oi.getProduct().getProductId());
    		        if (!imageUrlList.isEmpty()) {
    		        	pDTO.setImageUrl(imageUrlList.get(0));
    		        	oi.setProduct(pDTO);
    		        }
    		    } catch (IndexOutOfBoundsException e) {
    		        e.printStackTrace();
    		    }
    			oiList.add(oi);
    	}
    	
    	
    	return ResponseEntity.ok(oiList);
    }
    public Account getAccountAuth() {
		return aDAO.getByUserName(UserUtils.getUser().getUsername());
	}
}
