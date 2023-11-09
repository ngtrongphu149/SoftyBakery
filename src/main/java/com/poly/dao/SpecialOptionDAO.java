package com.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.models.SpecialOption;

public interface SpecialOptionDAO extends JpaRepository<SpecialOption,Integer> {
    
}
