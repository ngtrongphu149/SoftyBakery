package com.poly.services.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.poly.dao.ProductDAO;
import com.poly.entities.Product;
import com.poly.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    
    ProductDAO pDAO;

    @Override
    public Product add(Product product) {
        Product existingProduct = pDAO.findById(product.getProductId());
        if (existingProduct != null) {
            product.setProductId(pDAO.findTopProductId() + 1);
            pDAO.save(product);
            return product;
        }
        return null;
    }

    @Override
    public Product update(Product product) {
        if (pDAO.findById(product.getProductId()) != null) {
            pDAO.save(product);
            return product;
        }
        return null;
    }

    @Override
    public void delete(int productId) {
        Product existingProduct = pDAO.findById(productId);
        if (existingProduct != null) {
            pDAO.deleteById(productId);
        }
    }
}
