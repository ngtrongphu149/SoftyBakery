package com.poly.services;

import org.springframework.stereotype.Service;

import com.poly.entities.Product;

@Service
public interface ProductService {
    Product add(Product product);
    Product update(Product product);
    void delete(int productId);
}
