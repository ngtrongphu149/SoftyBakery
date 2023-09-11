package com.poly.Controlles;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.dao.CategoryDAO;
import com.poly.dao.ProductDAO;
import com.poly.dao.ProductImageDAO;
import com.poly.entities.ProductDTO;
import com.poly.model.Category;
import com.poly.model.Product;

import DB.DataSharing;

@Controller
public class ProductController {
	@Autowired
	ProductDAO pDAO;
	@Autowired
	CategoryDAO cDAO;
	@Autowired
	ProductImageDAO piDAO;

	@GetMapping("/product/")
	public String roduct(Model model) {
		
		return "product";
	}
	@GetMapping("/product/detail/{id}")
	public String product_detail(@PathVariable("id") int id, Model model) {
		
		model.addAttribute("piList", piDAO.getProductImagesByProductId(pDAO.getById(id)));
		model.addAttribute("product", pDAO.getProductById(id));
		model.addAttribute("img", piDAO.getProductImagesByProductId(pDAO.getById(id)).get(0));
		return "product-detail";
	}
}
