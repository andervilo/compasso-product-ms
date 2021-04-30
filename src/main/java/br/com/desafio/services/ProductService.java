package br.com.desafio.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.desafio.entities.Product;
import br.com.desafio.repositories.ProductRepository;
import br.com.desafio.specifications.ProductSpecification;
import javassist.NotFoundException;

@Service
public class ProductService {
	
	private ProductRepository productRepository;
	
	public ProductService(ProductRepository _productRepository){
		productRepository = _productRepository;
	}
	
	public Product findById(UUID id) throws NotFoundException{
		Optional<Product> oldProduct = productRepository.findById(id);
		if(!oldProduct.isPresent()) throw new NotFoundException("");
		
		return oldProduct.get();
	}
	
	public List<Product> findAll(){
		
		return productRepository.findAll();
	}
	
	public List<Product> findAllBySearch(String q, BigDecimal min_price,BigDecimal max_price) {
		return productRepository.findAll(ProductSpecification.getProduct(q, min_price, max_price));
	}
	
	public Product addProduct(Product product) {
		return productRepository.saveAndFlush(product);
	}

	public Product updateProduct(UUID id, Product product) throws NotFoundException {
		Optional<Product> oldProduct = productRepository.findById(id);
		
		if(!oldProduct.isPresent()) throw new NotFoundException("");
		
		Product oldProductToUpdate = oldProduct.get();
		
		oldProductToUpdate.setDescription(product.getDescription());
		oldProductToUpdate.setName(product.getName());
		oldProductToUpdate.setPrice(product.getPrice());
		
		return productRepository.saveAndFlush(oldProductToUpdate);
	}

	public void deleteProduct(UUID id) throws NotFoundException {
		Optional<Product> oldProduct = productRepository.findById(id);
		
		if(!oldProduct.isPresent()) throw new NotFoundException("");
		
		productRepository.deleteById(id);
	}

}
