package com.poly.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.dao.AccountDAO;
import com.poly.model.Account;
import DB.UserUtils;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/rest/user")
public class AccountRestController {
    @Autowired
    AccountDAO aDAO;
    private Account userInfo = null;
	@Autowired
	private PasswordEncoder passwordEncoder;
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
	@PutMapping()
	public ResponseEntity<Account> editProfile(Model model,@RequestBody Account user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		aDAO.save(user);
		return ResponseEntity.ok(user);
	}


	public Account getAccountAuth() {
		if(UserUtils.getUser() == null) return null;
		return aDAO.getByUserName(UserUtils.getUser().getUsername());
	}
}

