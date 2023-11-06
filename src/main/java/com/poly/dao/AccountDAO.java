package com.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poly.models.Account;

@Repository
public interface AccountDAO extends JpaRepository<Account, String>{
    
}
