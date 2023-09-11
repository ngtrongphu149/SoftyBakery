package com.poly.Controlles;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.dao.AccountDAO;
import com.poly.dao.ProductDAO;
import com.poly.model.Account;


@Controller
public class HomeController {
	@Autowired ProductDAO pDAO;
	@Autowired AccountDAO aDAO;
    @RequestMapping("/")
    public String index2() {
        return "redirect:/home";
    }
    @RequestMapping("/home")
    public String index(Model model) {
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
            Account account = aDAO.findByUserName(username).orElse(null);

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

