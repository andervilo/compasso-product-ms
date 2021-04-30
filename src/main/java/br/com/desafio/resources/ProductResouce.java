package br.com.desafio.resources;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.entities.Product;
import br.com.desafio.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductResouce {
	
	private ProductService productService;
	
	public ProductResouce( ProductService _productService) {
		productService = _productService;
	}
	
	@GetMapping
	public ResponseEntity<List<Product>> getAll(){
		return ResponseEntity.ok(productService.findAll());
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<Product>> getAllbysearch(
			@RequestParam(required = false, defaultValue = "") String q,  
			@RequestParam(required = false) BigDecimal min_price, 
			@RequestParam(required = false) BigDecimal max_price) {
		return ResponseEntity.ok(productService.findAllBySearch2(q, min_price, max_price));
	}

}
