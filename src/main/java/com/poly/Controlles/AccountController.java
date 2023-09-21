package com.poly.Controlles;

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

import com.poly.dao.AccountDAO;
import com.poly.model.Account;
import com.poly.services.FileStorageService;

import DB.UserUtils;

@Controller
public class AccountController {
	@Autowired AccountDAO aDAO;
	@Autowired FileStorageService fileService;
	
	private String UPLOAD_DIR = "C:\\Users\\lunba\\SoftyBakery\\src\\main\\resources\\static\\images\\accountPhoto";
	
	@GetMapping("/register")
	public String register(Model model) {
		Account a = new Account();
		a.setAccountId(aDAO.getTopAccountId()+1);
		model.addAttribute("user",a);
		return "register";
	}
	@PostMapping("/register")
	public String register1(Model model, @ModelAttribute("user") Account a) {
		
		a.setAccountId(aDAO.getTopAccountId()+1);
		a.setAdmin(false);
		a.setPassword(passwordEncoder().encode(a.getPassword()));
		a.setPhoto("noImage.jpg");
		System.out.println(a.toStringDetail());
		aDAO.save(a);
		return "login";
	}
	
	@GetMapping("/login")
	public String login() {

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
		return "profile-edit";
	}
	@PostMapping("/profile/edit")
	public String editProfile(@RequestParam("file") MultipartFile file,Model model) {
	    try {
	        Account user = getAccountAuth();
	        if(!file.isEmpty()) {
	        	Path uploadDir = Paths.get(UPLOAD_DIR);
	            Files.createDirectories(uploadDir);
		        Path filePath = uploadDir.resolve(file.getOriginalFilename());
	            Files.write(filePath, file.getBytes());
	            user.setPhoto(file.getOriginalFilename());
	        }
	        aDAO.save(user);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }

	    return "redirect:/profile";
	}

	
	
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	public Account getAccountAuth() { 
		return aDAO.getByUserName(UserUtils.getUser().getUsername());
	}
}
