package com.poly.Controlles;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.poly.dao.*;
import com.poly.entities.*;
import com.poly.model.Account;
import com.poly.model.Order;
import com.poly.model.OrderItem;
import com.poly.services.*;

import DB.UserUtils;

@Controller
public class OrderController {
	@Autowired
    CartService cart;
    @Autowired
    AccountDAO aDAO;
    @Autowired
    OrderDAO oDAO;
    @Autowired
    OrderItemDAO oiDAO;
	@GetMapping("/order")
    public String index(Model model) {
		Account acc = getAccountAuth();
		for(ProductDTO p : cart.getItems().values()) {
			System.out.println(p.toString());
		}
		model.addAttribute("user", acc);
		model.addAttribute("cart", cart.getItems().values());
		
		model.addAttribute("amount", cart.getAmount());
        return "order";
    }
	@PostMapping("/order")
	public String payment1(Model model, @ModelAttribute("user") Account user) {
		if(getAccountAuth() != null) {
	    Account account = getAccountAuth();
	    LocalDateTime date = LocalDateTime.now();
	    
	    Order o = new Order();
	    o.setOrderId(oDAO.findTopOrderId()+1);
	    o.setOrderDate(date);
	    o.setAccount(account);
	    o.setAddress(account.getAddressDetail()+" ,"+account.getAddress());
	    o.setTotalAmount(cart.getAmount());
	    o.setStatus("Đang chờ");
	    oDAO.save(o);

	    for (ProductDTO d : cart.getItems().values()) {
	        OrderItem oi = new OrderItem();
	        oi.setOrderItemId(oiDAO.findTopOrderItemId()+1);
	        oi.setOrder(o);
	        oi.setProduct(d.getProduct());
	        oi.setPrice(d.getPrice());
	        oi.setQuantity(d.getQuantity());
	        oi.toString();
	        oiDAO.save(oi);
	    } 
	    
	    model.addAttribute("o", o);
	    model.addAttribute("user", account);
	    
		}
	    return "order-success";
	}
	public Account getAccountAuth() {
		return aDAO.getByUserName(UserUtils.getUser().getUsername());
	}

}