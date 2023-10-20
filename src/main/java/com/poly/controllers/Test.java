package com.poly.controllers;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.daos.*;
import com.poly.models.*;
import com.poly.utils.UploadUtil;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import staticvariable.UserUtils;


@Controller
public class Test {
	@Autowired CategoryDAO cDAO;
	@Autowired AccountDAO aDAO;
	@Autowired ProductDAO pDAO;
	@Autowired ProductImageDAO piDAO;
	@Autowired OrderDAO oDAO;
	@Autowired OrderItemDAO oiDAO;
	@Autowired ReviewDAO rDAO;
	@Autowired PasswordEncoder passwordEncoder;
	@Autowired UploadUtil uploadUtil;
	@GetMapping("/test")
	public String test(Model model) {
		// File file = new File("src\\main\\resources\\static\\images\\accountPhoto\\download.png");
		// uploadUtil.upload(file);

		setPassword();
		return "test"; 
	} 




	public void saveAccount(Account a) {
		a.setAccountId(10004);
		a.setAddress("ở đâu còn lâu mới nói");
		a.setAdmin(false);
		a.setEmail("user@gmail.com");
		a.setFullName("user test");
		a.setPassword(passwordEncoder.encode("123"));
		a.setPhoneNumber("08888888");
		a.setUsername("user");
		aDAO.save(a);
	}

	public Account getAccountAuth() {
		return aDAO.getByUserName(UserUtils.getUser().getUsername());
	}

	public void testAccountInOrder() {
		Order o = oDAO.findById(10020).get();
		if (o.getAccount() == null) {
			System.out.println("ngu");
		} else {
			System.out.println(o.toString());
		}
	}

	public String saveAccountPhoto(String photo) {
		Account a = getAccountAuth();
		a.setPhoto(photo);
		aDAO.save(a);
		System.out.println(a.getPhoto());
		return a.getPhoto();
	}
	public void setPassword() {
		for(Account a : aDAO.findAll()) {
			a.setPassword(passwordEncoder.encode("123"));
			aDAO.save(a);
		}
		System.out.println("nghia ngu");
	}
}
