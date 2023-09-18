package com.poly.Controlles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.poly.dao.AccountDAO;
import com.poly.model.Account;
import com.poly.services.FileStorageService;

import DB.UserUtils;

@Controller
public class AccountController {
	@Autowired AccountDAO aDAO;
	@Autowired FileStorageService fileService;
	
	private String UPLOAD_DIR = "C:\\Java Workspace\\SoftyBakery\\src\\main\\resources\\static\\images\\accountPhoto";
	
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
		a.setAdmin(false);
		a.setPassword(passwordEncoder().encode(a.getPassword()));
		a.setPhoto("noImage.jpg");
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
	
	@GetMapping("/profile")
	public String profile(Model model) {
		Account a = getAccountAuth();
		
		
		model.addAttribute("user", a);
		return "profile";
	}
	@GetMapping("/profile/edit")
	public String editProfile(Model model) {
		Account user = getAccountAuth();
		
		
		
		
		model.addAttribute("user", user);
		return "edit-profile";
	}
	@PostMapping("/profile/edit")
	public String editProfile(@RequestParam("file") MultipartFile file,Model model, @ModelAttribute("user") Account user) {
	    try {
	        if (!user.getPassword().isEmpty()) {
	            user.setPassword(passwordEncoder().encode(user.getPassword()));
	        } else {
	            Account existingUser = aDAO.findByUserName(user.getUsername());
	            user.setPassword(existingUser.getPassword());
	        }
	        aDAO.save(user);
	        if(!file.isEmpty()) {
	        	Path uploadDir = Paths.get(UPLOAD_DIR);
	            Files.createDirectories(uploadDir);
		        Path filePath = uploadDir.resolve(file.getOriginalFilename());
	            Files.write(filePath, file.getBytes());
	            user.setPhoto(file.getOriginalFilename());
	        }
	        aDAO.save(user);
	        System.out.println(user.toStringDetail());
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }

	    return "redirect:/profile";
	}

	
	
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	public Account getAccountAuth() { 
		return aDAO.findByUserName(UserUtils.getUser().getUsername());
	}
}
