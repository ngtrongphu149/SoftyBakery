package com.poly.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.poly.dao.AccountDAO;
import com.poly.entities.AccountToUserDetails;
import com.poly.entities.Role;
import com.poly.model.Account;

@Component
public class AccountService implements UserDetailsService {
    @Autowired
    private AccountDAO aDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> ad = aDAO.findByUserName(username);
        
        if (ad.isPresent()) {
            return new AccountToUserDetails(ad.get());
        } else {
            throw new UsernameNotFoundException("User not found: " + username);
        }
    }
}
