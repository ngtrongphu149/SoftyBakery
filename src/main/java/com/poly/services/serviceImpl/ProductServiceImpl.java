package com.poly.services.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dao.ProductDAO;
import com.poly.dao.ProductImageDAO;
import com.poly.models.Product;
import com.poly.models.ProductImage;
import com.poly.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductDAO pDAO;
    @Autowired
    ProductImageDAO piDAO;

    @Override
    public Product add(Product product) {
        Product existingProduct = pDAO.getById(product.getProductId());
        if (existingProduct != null) {
            pDAO.save(product);
            return product;
        }
        // System.out.println(product.toString());
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
        Product existingProduct = pDAO.getById(productId);
        if (existingProduct != null) {
            for (ProductImage pi : existingProduct.getProductImages()) {
                piDAO.delete(pi);
            }
            pDAO.deleteById(productId);
        }
    }
}
