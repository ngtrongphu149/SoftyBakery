package com.poly.controllers;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.dao.*;
import com.poly.models.*;
import com.poly.services.*;
import com.poly.utils.*;

@Controller
public class Test {
	@Autowired
	CategoryDAO cDAO;
	@Autowired
	AccountDAO aDAO;
	@Autowired
	ProductDAO pDAO;
	@Autowired
	ProductImageDAO piDAO;
	@Autowired
	OrderDAO oDAO;
	@Autowired
	OrderItemDAO oiDAO;
	@Autowired
	ReviewDAO rDAO;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	UploadMediaImgurUtil uploadUtil;
	@Autowired
	AccountService accountService;
	@Autowired
	JsonReaderUtil jsonReaderUtil;
	@GetMapping("/test")
	public String test(Model model) {
		System.out.println(jsonReaderUtil.write( accountService.getAccountAuth() ));
		setPassword();
		return "test";
	}

	public Account getAccountAuth() {
		return accountService.getAccountAuth();
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
		for (Account a : aDAO.findAll()) {
			a.setPassword(passwordEncoder.encode("123"));
			aDAO.save(a);
		}
		System.out.println("nghia ngu");
	}
}
