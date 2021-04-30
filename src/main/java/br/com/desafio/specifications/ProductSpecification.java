package br.com.desafio.specifications;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import br.com.desafio.entities.Product;

public class ProductSpecification {
	
	private static Specification<Product> getProductByName(String q) {
		return (root, query, cb)->{
			return cb.like(cb.upper(root.get("name")), "%"+q.toUpperCase()+"%");
		}; 
	}
	
	private static Specification<Product> getProductByDescription(String q) {
		return (root, query, cb)->{
			return cb.like(cb.upper(root.get("description")), "%"+q.toUpperCase()+"%");
		}; 
	}
	
	private static Specification<Product> getProductByMinPrice(BigDecimal min_price) {
		return (root, query, cb)->{
			return cb.greaterThanOrEqualTo(root.get("price"), min_price);
		}; 
	}
	
	private static Specification<Product> getProductByMaxPrice(BigDecimal max_price) {
		return (root, query, cb)->{
			return cb.lessThanOrEqualTo(root.get("price"), max_price);
		}; 
	}
	
	private static Specification<Product> getProductByPriceRange(BigDecimal min_price, BigDecimal max_price) {
		return Specification.where(getProductByMinPrice(min_price)).and(getProductByMaxPrice(max_price)); 
	}
	
	private static Specification<Product> getProductByNameOrDescription(String q) {
		return Specification.where(getProductByName(q)).or(getProductByDescription(q)); 
	}
	
	public static Specification<Product> getProduct(String q, BigDecimal min_price, BigDecimal max_price) {
		if(min_price == null) min_price = BigDecimal.ZERO;
		
		if(max_price == null) max_price = BigDecimal.ZERO;
		
		if(q.equals(null)) q = "";
		
		if(!q.equals("") && !min_price.equals(BigDecimal.ZERO) && !max_price.equals(BigDecimal.ZERO)) {
			
			return Specification.where(getProductByNameOrDescription(q)).and(getProductByPriceRange(min_price, max_price)); 
			
		}else if(!q.equals("") && !min_price.equals(BigDecimal.ZERO) && max_price.equals(BigDecimal.ZERO)) {
			
			return Specification.where(getProductByNameOrDescription(q)).and(getProductByMinPrice(min_price));
			
		}else if(!q.equals("") && min_price.equals(BigDecimal.ZERO) && !max_price.equals(BigDecimal.ZERO)) {
			
			return Specification.where(getProductByNameOrDescription(q)).and(getProductByMaxPrice(max_price));
			
		}else if(q.equals("") && !min_price.equals(BigDecimal.ZERO) && !max_price.equals(BigDecimal.ZERO)) {
			
			return Specification.where(getProductByPriceRange(min_price, max_price));
			
		}else if(q.equals("") && !min_price.equals(BigDecimal.ZERO) && max_price.equals(BigDecimal.ZERO)) {
			
			return Specification.where(getProductByMinPrice(min_price));
			
		}else if(q.equals("") && min_price.equals(BigDecimal.ZERO) && !max_price.equals(BigDecimal.ZERO)) {
			
			return Specification.where(getProductByMaxPrice(max_price));
			
		}
		
		return Specification.where(getProductByNameOrDescription(q)); 
	}

}
