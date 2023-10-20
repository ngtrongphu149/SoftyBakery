package com.poly.services.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.poly.daos.ProductDAO;
import com.poly.dto.ProductDTO;
import com.poly.services.CartService;
import com.poly.services.LocalStorageService;


@Service
@SessionScope
public class CartServiceImplement implements CartService {
	@Autowired LocalStorageService lsService;
	@Autowired ProductDAO pDAO;
	
	Map<Integer, ProductDTO> map = new HashMap<>();
	
    @Override
    public void add(Integer id) {
    	
        if (!map.containsKey(id)) {
            ProductDTO dto = new ProductDTO(pDAO.getById(id));
            dto.setProductId(id);
            map.put(id, dto);
        } else {
            update(id, "plus");
        }
    }

    @Override
    public void remove(Integer id) {
        map.remove(id);
    }

    @Override
    public void update(Integer id, String qty) {
        ProductDTO item = map.get(id);
        if (item != null) {
            if ("dis".equals(qty) && item.getQuantity() > 1) {
                item.setQuantity(item.getQuantity() - 1);
            } else if ("plus".equals(qty) && item.getQuantity() < 10) {
                item.setQuantity(item.getQuantity() + 1);
            }
        }

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
        return map.values().stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();
    }

    @Override
    public ProductDTO getItem(Integer id) {
        return map.get(id);
    }
    @Override
    public void toString1() {
    	if(map.values().size()==0) {
    		System.out.println("Cart rỗng!");
    	}
    	for(ProductDTO p : map.values()) {
    		System.out.println(p.toString());
    	}
    }
}
    
