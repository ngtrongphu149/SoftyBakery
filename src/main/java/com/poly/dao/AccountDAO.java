package com.poly.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.model.Account;

public interface AccountDAO extends JpaRepository<Account, Integer> {
	@Query("SELECT a.accountId FROM Account a ORDER BY a.accountId DESC LIMIT 1")
    Integer getTopAccountId();
	
	@Query("SELECT a FROM Account a WHERE a.username = ?1")
	Account getByUserName(String username);
	
	@Query("SELECT a FROM Account a WHERE a.email = ?1")
	Account getByEmail(String email);
	
	
}
