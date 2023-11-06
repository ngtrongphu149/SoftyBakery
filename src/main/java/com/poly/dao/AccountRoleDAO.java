package com.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poly.models.AccountRole;

@Repository
public interface AccountRoleDAO extends JpaRepository<AccountRole, Integer>{
    
}
