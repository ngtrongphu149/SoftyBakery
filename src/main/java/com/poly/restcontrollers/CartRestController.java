package com.poly.restcontrollers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.dao.CategoryDAO;
import com.poly.dao.ProductDAO;
import com.poly.dto.ProductDTO;
import com.poly.services.CartService;

@RestController
@RequestMapping("/rest/cart")
public class CartRestController {
	@Autowired CartService cart;
	@Autowired CategoryDAO cDAO;
	@Autowired ProductDAO pDAO;
	@GetMapping
	public ResponseEntity<Collection<ProductDTO>> cart(Model model) {
		synchronized (cart) {
	        List<ProductDTO> pDTOList = new ArrayList<>();
	        for (ProductDTO p : cart.getItems().values()) {
	            pDTOList.add(p);
	        }
	        return ResponseEntity.ok(pDTOList);
	    }
	}
	@GetMapping("/add/{id}")
	public ResponseEntity<ProductDTO> add(@PathVariable("id") Integer id) {
		cart.add(id);
		ProductDTO pDTO = cart.getItem(id);
		pDTO.setProduct(pDAO.getById(id));
		return ResponseEntity.ok(pDTO);
	}
	@GetMapping("/dis/{id}")
	public ResponseEntity<ProductDTO> dis(@PathVariable("id") Integer id) {
		cart.update(id, "dis");
		ProductDTO pDTO = new ProductDTO();
		pDTO.setProduct(pDAO.getById(id));
		return ResponseEntity.ok(pDTO);
	}
	@GetMapping("/plus/{id}")
	public ResponseEntity<ProductDTO> plus(@PathVariable("id") Integer id) {
		cart.update(id, "plus");
		ProductDTO pDTO = new ProductDTO();
		pDTO.setProduct(pDAO.getById(id));
		return ResponseEntity.ok(pDTO);
	}
	@GetMapping("/delete/{id}")
	public void delete(@PathVariable("id") Integer id) {
	    synchronized (cart) {
	        Iterator<Map.Entry<Integer, ProductDTO>> iterator = cart.getItems().entrySet().iterator();
	        while (iterator.hasNext()) {
	            Map.Entry<Integer, ProductDTO> entry = iterator.next();
	            if (entry.getKey().equals(id)) {
	                iterator.remove();
	                break;
	            }
	        }
	    }
	}
	
	@GetMapping("/clear")
	public void clear() {
		cart.clear();
	}
}
