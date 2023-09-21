package com.poly.RestController;

import java.time.LocalDateTime;
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
import com.poly.dao.*;
import com.poly.entities.*;
import com.poly.model.Account;
import com.poly.model.Product;
import com.poly.model.Review;

import DB.UserUtils;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/rest/review")
public class ReviewRestController {
	@Autowired ProductDAO pDAO;
	@Autowired AccountDAO aDAO;
	@Autowired ReviewDAO rDAO;
	LocalDateTime date = LocalDateTime.now();
	@GetMapping("/{id}")
	public ResponseEntity<List <Review>> findById(@PathVariable("id") int id) {
		List<Review> reviewList = rDAO.getAllReviewsByProductId(id);
		return ResponseEntity.ok(reviewList);
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<String> post(@PathVariable int id,@RequestBody Review review) {
	    Product p = pDAO.getById(id);
	    review.setReviewId(rDAO.getTopReviewId()+1);
		review.setAccount(getAccountAuth());
		review.setReviewDate(date);
		review.setProduct(p);
		rDAO.save(review);

		Review r = new Review();

	    return ResponseEntity.ok("ok");
	}
	@GetMapping("/avgRating/{productId}")
	public ResponseEntity<Double> getAvgRating(@PathVariable("productId") int productId) {
		return ResponseEntity.ok(rDAO.getAvgRating(productId));
	}
	public Account getAccountAuth() {
		if(UserUtils.getUser() == null) return null;
		return aDAO.getByUserName(UserUtils.getUser().getUsername());
	}
}