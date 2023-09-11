package com.poly.Controlles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.dao.AccountDAO;
import com.poly.model.Account;

import DB.UserUtils;

@Controller
public class AccountController {
	@Autowired AccountDAO aDAO;
	
	
	@GetMapping("/register")
	public String register(Model model) {
		Account a = new Account();
		a.setAccountId(aDAO.findTopAccountId()+1);
		model.addAttribute("user",a);
		return "register";
	}
	@PostMapping("/register")
	public String register1(Model model, @ModelAttribute("user") Account a) {
		
		a.setAccountId(aDAO.findTopAccountId()+1);
		a.setAddress(a.getAddress());
		a.setAdmin(false);
		a.setEmail(a.getEmail());
		a.setFullName(a.getFullName());
		a.setPassword(passwordEncoder().encode(a.getPassword()));
		a.setPhoneNumber(a.getPhoneNumber());
		a.setUsername(a.getUsername());
		System.out.println(a.toStringDetail());
		aDAO.save(a);
		return "login";
	}
	
	@GetMapping("/login")
	public String login() {
//		if(error) {
//			System.out.println("khong co error");
//			if(error == true) {
//				return "login";
//			} else if(error == false) {
//				return "home";
//			}
//		}
		return "login";
	}
	@GetMapping("/login/")
	public String loginValidation(@RequestParam("error") Boolean error,
								  Model model) {
			if(error == true) {
				String message = "Sai tên đăng nhập hoặc mật khẩu!";
				model.addAttribute("message",message);
				return "login";
			}
		return "login";
	}
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	public Account getAccountAuth() {
		return aDAO.findByUserName(UserUtils.getUser().getUsername()).get();
	}
}
