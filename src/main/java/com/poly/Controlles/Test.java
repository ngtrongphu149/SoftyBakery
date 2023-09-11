package com.poly.Controlles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.poly.dao.AccountDAO;
import com.poly.dao.CategoryDAO;
import com.poly.dao.OrderDAO;
import com.poly.dao.OrderItemDAO;
import com.poly.dao.ProductDAO;
import com.poly.dao.ProductImageDAO;
import com.poly.entities.Role;
import com.poly.model.Account;
import com.poly.model.Category;
import com.poly.model.Order;
import com.poly.model.OrderItem;
import com.poly.model.Product;
import com.poly.model.ProductImage;

import DB.UserUtils;

@Controller
public class Test {
	@Autowired CategoryDAO cDAO;
	@Autowired AccountDAO aDAO;
	@Autowired ProductDAO pDAO;
	@Autowired ProductImageDAO piDAO;
	@Autowired OrderDAO oDAO;
	@Autowired OrderItemDAO oiDAO;
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@GetMapping("/test")
	public String ngu(Model model) {
		Account a = getAccountAuth();
		a.setPhoto("cuong.jpg");
		aDAO.save(a);
		System.out.println(a.getPhoto());
		return "home";
	}
	
	public void saveAccount(Account a) {
		a.setAccountId(10004);
		a.setAddress("ở đâu còn lâu mới nói");
		a.setAdmin(false);
		a.setEmail("user@gmail.com");
		a.setFullName("user test");
		a.setPassword(passwordEncoder().encode("123"));
		a.setPhoneNumber("08888888");
		a.setUsername("user");
		aDAO.save(a);
	}
	public Account getAccountAuth() {
		return aDAO.findByUserName(UserUtils.getUser().getUsername()).get();
	}
	public void testAccountInOrder() {
		Order o = oDAO.findById(10020).get();
		if(o.getAccount() == null ) {
			System.out.println("ngu");
		} else {
			System.out.println(o.toString());
		}
	}
}
