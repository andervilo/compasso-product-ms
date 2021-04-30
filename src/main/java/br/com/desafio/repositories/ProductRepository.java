package br.com.desafio.repositories;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.desafio.entities.Product;

public interface ProductRepository extends JpaRepository<Product, UUID>, JpaSpecificationExecutor<Product> {
	
	@Query("SELECT "
			+ "p "
			+ "FROM Product p "
			+ "WHERE (UPPER(p.name) LIKE UPPER(CONCAT('%', :q, '%')) OR UPPER(p.description) LIKE UPPER(CONCAT('%', :q, '%'))) ")
	List<Product> findAllByKeyword(@Param("q") String q);
	
	@Query("SELECT "
			+ "p "
			+ "FROM Product p "
			+ "WHERE ((UPPER(p.name) LIKE UPPER(CONCAT('%', :q, '%')) OR UPPER(p.description) LIKE UPPER(CONCAT('%', :q, '%'))) AND (p.price >=:min_price AND p.price <=:max_price)) "
			+ "OR ((UPPER(p.name) LIKE UPPER(CONCAT('%', :q, '%')) OR UPPER(p.description) LIKE UPPER(CONCAT('%', :q, '%'))) OR (p.price >=:min_price))"
			+ "OR ((UPPER(p.name) LIKE UPPER(CONCAT('%', :q, '%')) OR UPPER(p.description) LIKE UPPER(CONCAT('%', :q, '%'))) OR (p.price <=:max_price))")
	List<Product> findAllByKeywordAndPriceRange(@Param("q") String q, @Param("min_price") BigDecimal min_price, @Param("max_price") BigDecimal max_price);
	
	

}
