package com.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poly.models.SpecialOption;

@Repository
public  interface SpecialOptionDAO extends JpaRepository<SpecialOption, Integer> {
    
}
