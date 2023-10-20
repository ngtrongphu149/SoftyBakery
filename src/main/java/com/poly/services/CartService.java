package com.poly.services;
import java.util.Map;

import com.poly.dto.ProductDTO;


public interface CartService {

	void add(Integer id);

	void remove(Integer id);

	void update(Integer id, String qty);

	void clear();

	Map<Integer, ProductDTO> getItems();

	int getCount();

	double getAmount();
	
	ProductDTO getItem(Integer id);
	
	void toString1();
}
