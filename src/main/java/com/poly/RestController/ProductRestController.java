package com.poly.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.poly.dao.*;
import com.poly.entities.*;
import com.poly.model.Product;
import com.poly.model.ProductImage;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/rest/product")
public class ProductRestController {
	@Autowired ProductDAO pDAO;
	@Autowired CategoryDAO cDAO;
	@Autowired OrderItemDAO oiDAO;
	@Autowired ProductImageDAO piDAO;
	
	@GetMapping
	public ResponseEntity<List<ProductDTO>> page() {
		List<ProductDTO> pDTOs = new ArrayList<>();
		for (Product p : pDAO.findAll()) {
		    ProductDTO pDTO = new ProductDTO(p);
		    
		    try {
		        List<String> imageUrlList = pDAO.getImageUrlByProductId(p.getProductId());
		        if (!imageUrlList.isEmpty()) {
		            pDTO.setImageUrl(imageUrlList.get(0));
		        }
		    } catch (IndexOutOfBoundsException e) {
		        e.printStackTrace();
		    }
		    
		    pDTOs.add(pDTO);
		}

		return ResponseEntity.ok(pDTOs);
	}
	@GetMapping("{id}")
	public ResponseEntity<Product> findById(@PathVariable("id") String id) {
		Optional<Product> optional = pDAO.findById(Integer.parseInt(id));
		if(!optional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(optional.get());
	}
	@GetMapping("/selectedProductId")
	public ResponseEntity<Integer> getProductSelected() {
		return ResponseEntity.ok(DB.DataSharing.selectedProductId);
	}
	
	@GetMapping("/top")
	public ResponseEntity<Integer> findTopProductId() {
		int num = pDAO.findTopProductId() + 1;
		return ResponseEntity.ok(num);
	}
	
	@GetMapping("/c/{categoryId}")
	public ResponseEntity<List <Product>> findByCategory(@PathVariable("categoryId") int categoryId) {
		List<Product> products = pDAO.getProductByCategory(categoryId);
//		if(!products.isEmpty()) {
//			return ResponseEntity.notFound().build();
//		}
		return ResponseEntity.ok(products);
	}
	@PostMapping()
	public ResponseEntity<Product> post(@RequestBody ProductDTO pDTO) {
	    if (pDTO.getProductId() <= 0) {
	        return ResponseEntity.badRequest().build();
	    }
	    Optional<Product> existingProduct = pDAO.findById(pDTO.getProductId());
	    Product prd = new Product(pDTO);
	    if (existingProduct.isPresent()) {
	        return ResponseEntity.badRequest().build();
	    }
	    prd.setProductId(pDAO.findTopProductId()+1);
	    pDAO.save(prd);
	    return ResponseEntity.ok(pDTO);
	}


	@PutMapping("/{id}")
	public ResponseEntity<Product> put(
	    @PathVariable("id") String id,
	@RequestBody ProductDTO productDTO) {
	    if (productDTO.getProductId() != Integer.parseInt(id) || productDTO.getProductId() <= 0) {
	        return ResponseEntity.badRequest().build();
	    }
	    Product prd = new Product(productDTO);
	    pDAO.save(prd);
	
	return ResponseEntity.ok(productDTO);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") String id) {
	    int productId = Integer.parseInt(id);
	    Optional<Product> existingProduct = pDAO.findById(productId);
	    
	    if (!existingProduct.isPresent()) {
	        return ResponseEntity.notFound().build();
	    }

	    pDAO.deleteById(productId);
	    return ResponseEntity.ok().build();
	}
	@GetMapping("/")
	public ResponseEntity<List<ProductDTO>> pageProduct(Model model, 
			@RequestParam(required = false, name = "c") String categoryId,
			@RequestParam(required = false, name = "page", defaultValue = "0") Integer page,
			@RequestParam(required = false, name = "size", defaultValue = "4") Integer size,
			@RequestParam(required = false, name = "sort", defaultValue = "ASC") String sort,
			@RequestParam(required = false, name = "key") String key, 
			@RequestParam(required = false) Double minPrice,
			@RequestParam(required = false) Double maxPrice) {
		if (size <= 0) {
	        size = 4;
	    }
		if (!sort.equals("ASC") && !sort.equals("DESC")) {
	        sort = "ASC";
	    }
		List<Product> products = pDAO.findAll();
		List<ProductDTO> prdDTOList = new ArrayList<>();
		
		Page<Product> filteredProducts = filterAndPaginateProducts(products, key, minPrice, maxPrice, sort, page, size,
	            categoryId);

		for (Product p : filteredProducts) {
			ProductDTO pDTO = new ProductDTO(p);
			
			try {
		        List<String> imageUrlList = pDAO.getImageUrlByProductId(p.getProductId());
		        if (!imageUrlList.isEmpty()) {
		            pDTO.setImageUrl(imageUrlList.get(0));
		        }
		    } catch (IndexOutOfBoundsException e) {
		        e.printStackTrace();
		    }
			
			prdDTOList.add(pDTO);
        }
		
		return ResponseEntity.ok(prdDTOList);
	}
	
	private List<Product> filterByName(List<Product> products, String name) {
		return products.stream().filter(product -> product.getProductName().toLowerCase().contains(name.toLowerCase()))
				.collect(Collectors.toList());
	}

	private List<Product> filterByPriceRange(List<Product> products, double minPrice, double maxPrice) {
		return products.stream().filter(product -> product.getPrice() >= minPrice && product.getPrice() <= maxPrice)
				.collect(Collectors.toList());
	}

	private List<Product> filterByCategory(List<Product> products, int categoryId) {
		return products.stream().filter(product -> product.getCategory().getCategoryId() == categoryId)
				.collect(Collectors.toList());
	}

	private List<Product> sort(List<Product> products, boolean ascending) {
		Comparator<Product> priceComparator = Comparator.comparingDouble(Product::getPrice);
		if (!ascending) {
			priceComparator = priceComparator.reversed();
		}
		return products.stream().sorted(priceComparator).collect(Collectors.toList());
	}
	
	private Page<Product> filterAndPaginateProducts(List<Product> products, String name, Double minPrice,
			Double maxPrice, String sort, int page, int size, String categoryId) {
		List<Product> filteredProducts = products;

		if (name != null && !name.isEmpty()) {
			filteredProducts = filterByName(filteredProducts, name);
		}
		if (minPrice != null && maxPrice != null && minPrice >= 0 && maxPrice >= 0 && maxPrice >= minPrice) {
			filteredProducts = filterByPriceRange(filteredProducts, minPrice, maxPrice);
		}
		if (categoryId != null && !categoryId.isEmpty()) {
			filteredProducts = filterByCategory(filteredProducts, Integer.parseInt(categoryId));
		}
		if (sort.equals("ASC")) {
			filteredProducts = sort(filteredProducts, true);
		} else if (sort.equals("DESC")) {
			filteredProducts = sort(filteredProducts, false);
		}
		Pageable pageable = PageRequest.of(page, size);
		int totalItems = filteredProducts.size();
		int startIndex = (int) pageable.getOffset();
		int endIndex = Math.min(startIndex + size, totalItems);
		List<Product> items = filteredProducts.subList(startIndex, endIndex);

		return new PageImpl<>(items, pageable, totalItems);
	}
	
}
