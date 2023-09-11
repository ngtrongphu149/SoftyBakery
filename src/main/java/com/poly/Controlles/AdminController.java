package com.poly.Controlles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.dao.CategoryDAO;
import com.poly.dao.ProductDAO;
import com.poly.model.Product;


@Controller
public class AdminController {
	@Autowired
	ProductDAO pDAO;
	@Autowired
	CategoryDAO cDAO;
	
	
	@GetMapping("/admin/product")
	public String admin_product(Model model) {
		
		return "admin/admin-product";
	}
	@GetMapping("/admin/order")
	public String admin_order(Model model) {
		
		return "admin/admin-order";
	}
	
}
