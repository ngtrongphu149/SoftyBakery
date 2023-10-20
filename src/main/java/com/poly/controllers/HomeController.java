package com.poly.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.daos.AccountDAO;
import com.poly.daos.CategoryDAO;
import com.poly.daos.ProductDAO;
import com.poly.models.Account;


@Controller
public class HomeController {
	@Autowired ProductDAO pDAO;
	@Autowired AccountDAO aDAO;
	@Autowired CategoryDAO cDAO;
    @RequestMapping("/")
    public String index2() {
        return "redirect:/home";
    }
    @RequestMapping("/home")
    public String index(Model model) {
    	
    	model.addAttribute("c", cDAO.findAll());
    	model.addAttribute("count", pDAO.count());
    	model.addAttribute("p", pDAO.findAll());
    	printUserInfo();
        return "home";
    } 
    public void printUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
            Account account = aDAO.getByUserName(username);

            if (account != null) {
                System.out.println(account.toString());
            } else {
                System.out.println("User not found!");
            }
        } else {
            System.out.println("No user authenticated.");
        }
    }
}

