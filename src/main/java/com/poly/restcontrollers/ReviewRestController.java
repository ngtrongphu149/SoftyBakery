package com.poly.restcontrollers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.poly.dao.*;
import com.poly.entities.Account;
import com.poly.entities.Product;
import com.poly.entities.Review;
import com.poly.services.AccountService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/rest/review")
public class ReviewRestController {
	@Autowired ProductDAO pDAO;
	@Autowired AccountDAO aDAO;
	@Autowired ReviewDAO rDAO;
	@Autowired AccountService accountService;
	@GetMapping("/{id}")
	public ResponseEntity<List <Review>> findById(@PathVariable("id") int id) {
		List<Review> reviewList = rDAO.getAllReviewsByProductId(id);
		return ResponseEntity.ok(reviewList);
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<Review> post(@PathVariable int id,@RequestBody Review review) {
		
		LocalDateTime localDateTime = LocalDateTime.now();
		ZoneId zoneId = ZoneId.of("Asia/Ho_Chi_Minh");
		ZonedDateTime date = localDateTime.atZone(zoneId);

	    Product p = pDAO.getById(id);
	    review.setReviewId(rDAO.getTopReviewId()+1);
		review.setAccount(getAccountAuth());
		// review.setReviewDate(date.toLocalDateTime());
		review.setProduct(p);
		rDAO.save(review);

		Review r = review;

	    return ResponseEntity.ok(r);
	}
	@GetMapping("/avgRating/{productId}")
	public ResponseEntity<Double> getAvgRating(@PathVariable("productId") int productId) {
		return ResponseEntity.ok(rDAO.getAvgRating(productId));
	}
	public Account getAccountAuth() {
		return accountService.getAccountAuth();
	}
}
