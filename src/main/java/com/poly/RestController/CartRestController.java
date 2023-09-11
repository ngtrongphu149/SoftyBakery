package com.poly.RestController;

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
import com.poly.entities.ProductDTO;
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
	            p.setImageUrl(pDAO.getImageUrlByProductId(p.getProductId()).get(0));
	            pDTOList.add(p);
	        }
	        return ResponseEntity.ok(pDTOList);
	    }
	}
	@GetMapping("/add/{id}")
	public ResponseEntity<ProductDTO> add(@PathVariable("id") Integer id) {
		cart.add(id);
		return ResponseEntity.ok(new ProductDTO(pDAO.getById(id)));
	}
	@GetMapping("/dis/{id}")
	public ResponseEntity<ProductDTO> dis(@PathVariable("id") Integer id) {
		cart.update(id, "dis");
		return ResponseEntity.ok(new ProductDTO(pDAO.getById(id)));
	}
	@GetMapping("/plus/{id}")
	public ResponseEntity<ProductDTO> plus(@PathVariable("id") Integer id) {
		cart.update(id, "plus");
		return ResponseEntity.ok(new ProductDTO(pDAO.getById(id)));
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
