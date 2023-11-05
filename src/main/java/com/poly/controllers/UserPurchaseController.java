package com.poly.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import com.poly.dao.OrderDAO;
import com.poly.entities.Order;
import com.poly.services.AccountService;

@Controller
public class UserPurchaseController {
    @Autowired AccountService accountService;
    @Autowired OrderDAO oDAO;
    @GetMapping("/user/purchase")
    public String user_purchase(Model model) {
        model.addAttribute("user", accountService.getAccountAuth());
        model.addAttribute("orderList", oDAO.findOrderByAccountId(accountService.getAccountAuth().getAccountId()));
        return "order-history";
    }
}
