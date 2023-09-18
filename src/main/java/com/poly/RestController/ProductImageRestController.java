package com.poly.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.poly.dao.*;
import com.poly.entities.*;
import com.poly.model.Product;
import com.poly.model.ProductImage;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/rest/pi")
public class ProductImageRestController {
	@Autowired ProductDAO pDAO;
	@Autowired CategoryDAO cDAO;
	@Autowired OrderItemDAO oiDAO;
	@Autowired ProductImageDAO piDAO;
	
	@GetMapping("/{id}")
	public ResponseEntity<List <ProductImage>> oe(@PathVariable("id") int id) {
		Product p = pDAO.getById(id);
		List<ProductImage> piList = piDAO.getPIByProductId(p);
		return ResponseEntity.ok(piList);
	}	
}
