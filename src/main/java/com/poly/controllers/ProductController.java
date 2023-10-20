package com.poly.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.poly.daos.CategoryDAO;
import com.poly.daos.ProductDAO;
import com.poly.daos.ProductImageDAO;

@Controller
public class ProductController {
	@Autowired ProductDAO pDAO;
	@Autowired CategoryDAO cDAO;
	@Autowired ProductImageDAO piDAO;
	
	private final RestTemplate restTemplate;

    public ProductController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
	@GetMapping("/product/")
	public String product(Model model) {
		model.addAttribute("message", "Cart is empty, let's take a look at some products!");
		return "product"; 
	}
	@GetMapping("/product/detail/{id}")
	public String product_detail(@PathVariable("id") int id, Model model) {
		model.addAttribute("piList", piDAO.getProductImagesByProductId(pDAO.getById(id)));
		model.addAttribute("product", pDAO.getProductById(id));
		model.addAttribute("img", piDAO.getProductImagesByProductId(pDAO.getById(id)).get(0));
		staticvariable.DataSharing.selectedProductId = id;
		return "product-detail";
	}
}
