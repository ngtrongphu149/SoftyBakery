package com.poly.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.dao.AccountDAO;
import com.poly.model.Account;
import com.poly.model.Category;

import DB.UserUtils;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/rest/user")
public class UserRestController {
    @Autowired 
    AccountDAO aDAO;
    private Account userInfo = null;

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
	public Account getAccountAuth() {
		if(UserUtils.getUser() == null) return null;
		return aDAO.findByUserName(UserUtils.getUser().getUsername());
	}
}

