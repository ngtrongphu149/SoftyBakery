package com.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.models.Account;

public interface AccountDAO extends JpaRepository<Account, String> {
	@Query("SELECT a FROM Account a WHERE a.email = ?1")
	Account getByEmail(String email);
}
