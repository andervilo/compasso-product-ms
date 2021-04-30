package br.com.desafio.resources;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.entities.Product;
import br.com.desafio.services.ProductService;
import javassist.NotFoundException;

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
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getOne(@PathVariable UUID id) throws NotFoundException{
		return ResponseEntity.ok(productService.findById(id));
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<Product>> getAllbysearch(
			@RequestParam(required = false, defaultValue = "") String q,  
			@RequestParam(required = false) BigDecimal min_price, 
			@RequestParam(required = false) BigDecimal max_price) {
		return ResponseEntity.ok(productService.findAllBySearch(q, min_price, max_price));
	}
	
	@PostMapping
	public ResponseEntity<Product> create(@Valid @RequestBody Product product){
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(product));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Product> update(@PathVariable UUID id,@Valid @RequestBody Product product) throws NotFoundException{
		return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(id,product));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable UUID id) throws NotFoundException{
		productService.deleteProduct(id);
		return ResponseEntity.ok("");
	}
	
	

}
