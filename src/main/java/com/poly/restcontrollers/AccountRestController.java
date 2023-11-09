package com.poly.restcontrollers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.dao.AccountDAO;
import com.poly.dto.AccountDTO;
import com.poly.models.Account;
import com.poly.services.AccountService;
import com.poly.utils.PasswordUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/rest/user")
public class AccountRestController {
    @Autowired
    AccountDAO aDAO;
	@Autowired
	AccountService accountService;

	ObjectMapper ObjectMapper = new ObjectMapper();

    private Account userInfo = new Account();
	
    @GetMapping
    public ResponseEntity<Account> user() {
    	userInfo = null;
        if (userInfo == null) {
        	if(getAccountAuth() == null) {
        		return null;
        	}
        	userInfo = getAccountAuth();
        	return ResponseEntity.ok(userInfo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
	// @GetMapping("/findAll")
	// public String FindAll() throws IOException {
	// 	return ObjectMapper.writeValueAsString(aDAO.findAll());
	// }
	@GetMapping("/{username}")
	public ResponseEntity<Account> findByUsername(@PathVariable("username") String username) {
		return ResponseEntity.ok(aDAO.getById(username));
	}

	@PutMapping()
	public ResponseEntity<Account> editProfile(Model model,@RequestBody Account user) {
		user.setPassword(PasswordUtil.encode(user.getPassword()));
		aDAO.save(user);
		return ResponseEntity.ok(user);
	}


	public Account getAccountAuth() {
		return accountService.getAccountAuth();
	}
}

