package com.poly.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.model.Account;

public interface AccountDAO extends JpaRepository<Account, Integer> {
	@Query("SELECT a.accountId FROM Account a ORDER BY a.accountId DESC LIMIT 1")
    Integer findTopAccountId();
	
	@Query("SELECT a FROM Account a WHERE a.username = ?1")
	Account findByUserName(String username);
	
	@Query("SELECT a FROM Account a WHERE a.email = ?1")
	Account findByEmail(String email);
	
	
}
