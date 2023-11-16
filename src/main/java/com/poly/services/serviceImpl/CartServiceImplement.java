package com.poly.services.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.poly.dao.ProductDAO;
import com.poly.dto.ProductDTO;
import com.poly.models.Product;
import com.poly.services.CartService;
import com.poly.utils.LocalStorageUtil;

@Service
@SessionScope
public class CartServiceImplement implements CartService {
    @Autowired
    LocalStorageUtil lsService;
    @Autowired
    ProductDAO pDAO;

    Map<Integer, ProductDTO> map = new HashMap<>();
    @Override
    public void add(Integer id) {
        if (!map.containsKey(id)) {
            Product product = pDAO.findById(id).get();
            map.put(id, new ProductDTO(product, 1));
        } else {
            update(id, "plus");
        }
    }

    @Override
    public void update(Integer id, String modify) {
        ProductDTO item = map.get(id);
        if (item != null) {
            if ("dis".equals(modify) && item.getQuantity() > 1) {
                item.setQuantity(item.getQuantity() - 1);
            } else if ("plus".equals(modify) && item.getQuantity() < 10) {
                item.setQuantity(item.getQuantity() + 1);
            }
        }
    }

    @Override
    public void remove(Integer id) {
        map.remove(id);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Map<Integer, ProductDTO> getItems() {
        return map;
    }

    @Override
    public int getCount() {
        return map.values().stream().mapToInt(ProductDTO::getQuantity).sum();
    }

    @Override
    public double getAmount() {
        return map.values().stream().mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity()).sum();
    }

    @Override
    public ProductDTO getItem(Integer id) {
        return map.get(id);
    }
}
