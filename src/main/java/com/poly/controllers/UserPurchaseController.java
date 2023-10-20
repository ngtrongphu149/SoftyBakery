package com.poly.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserPurchaseController {
    @GetMapping("/user/purchase")
    public String user_purchase() {
        
        return "user_purchase";
    }
}
