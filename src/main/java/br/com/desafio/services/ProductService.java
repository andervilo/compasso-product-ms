package br.com.desafio.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.desafio.entities.Product;
import br.com.desafio.repositories.ProductRepository;
import br.com.desafio.specifications.ProductSpecification;

@Service
public class ProductService {
	
	private ProductRepository productRepository;
	
	public ProductService(ProductRepository _productRepository){
		productRepository = _productRepository;
	}
	
	public List<Product> findAll(){
		return productRepository.findAll();
	}

	public List<Product> findAllBySearch(String q, BigDecimal min_price,BigDecimal max_price) {
		return productRepository.findAllByKeywordAndPriceRange(q, min_price, max_price);
	}
	
	public List<Product> findAllBySearch2(String q, BigDecimal min_price,BigDecimal max_price) {
		return productRepository.findAll(ProductSpecification.getProduct(q, min_price, max_price));
	}

}
