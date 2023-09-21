package com.poly.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.poly.dao.AccountDAO;
import com.poly.entities.AccountToUserDetails;
import com.poly.model.Account;

@Component
public class AccountService implements UserDetailsService {
    @Autowired
    private AccountDAO aDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account ad = aDAO.getByUserName(username);
        
        if (ad != null) {
            return new AccountToUserDetails(ad);
        } else {
            throw new UsernameNotFoundException("User not found: " + username);
        }
    }
}