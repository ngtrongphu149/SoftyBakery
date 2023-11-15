package com.poly.restcontrollers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poly.dao.*;
import com.poly.dto.*;
import com.poly.models.Product;
import com.poly.services.ProductService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/rest/product")
public class ProductRestController {
	@Autowired
	ProductDAO pDAO;
	@Autowired
	CategoryDAO cDAO;
	@Autowired
	OrderItemDAO oiDAO;
	@Autowired
	ProductImageDAO piDAO;

	@Autowired
	ProductService productService;
	
	ObjectMapper ObjectMapper = new ObjectMapper();

	@GetMapping
	public ResponseEntity<List<ProductDTO>> page() {
		List<ProductDTO> pDTOs = pDAO.findAll().stream()
				.map(p -> {
					ProductDTO pDTO = new ProductDTO();
						pDTO.setProduct(p);
					return pDTO;
				})
				.collect(Collectors.toList());

		return ResponseEntity.ok(pDTOs);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> findById(@PathVariable("id") int id) {
		return ResponseEntity.ok(pDAO.getById(id));
	}

	@GetMapping("/c/{categoryId}")
	public ResponseEntity<List<Product>> findByCategory(@PathVariable("categoryId") int categoryId) {
		List<Product> products = pDAO.getProductByCategory(categoryId);
		return ResponseEntity.ok(products);
	}

	@PostMapping()
	public ResponseEntity<Product> post(@RequestBody Product product) {
		return ResponseEntity.ok(productService.add(product));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Product> put(@RequestBody Product product) {
		return ResponseEntity.ok(productService.update(product));
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") int id) {
		productService.delete(id);
	}

}
