package com.poly.restcontrollers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.poly.daos.*;
import com.poly.dto.*;
import com.poly.models.Category;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/rest/category")
public class CategoryRestController {
	@Autowired
	ProductDAO pDAO;
	@Autowired
	CategoryDAO cDAO;

	@GetMapping
	public ResponseEntity<List<Category>> findAll() {
		return ResponseEntity.ok(cDAO.findAll());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Category> findById(@PathVariable("id") String id) {
		Optional<Category> optional = cDAO.findById(Integer.parseInt(id));
		if(!optional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(optional.get());
	}
	@PostMapping()
	public ResponseEntity<Category> post(Category category) {
		if(cDAO.existsById(category.getCategoryId())) {
			return ResponseEntity.badRequest().build();
		}
		cDAO.save(category);
		return ResponseEntity.ok(category);
	}
	
	@DeleteMapping("id")
	public ResponseEntity<Category> delete(@PathVariable("id") String id) {
		if(!pDAO.existsById(Integer.parseInt(id))) {
			return ResponseEntity.notFound().build();
		}
		pDAO.deleteById(Integer.parseInt(id));
		return ResponseEntity.ok().build();
	}	
}
