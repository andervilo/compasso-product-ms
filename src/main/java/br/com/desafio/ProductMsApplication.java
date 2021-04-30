package br.com.desafio;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.desafio.entities.Product;
import br.com.desafio.repositories.ProductRepository;

@SpringBootApplication
public class ProductMsApplication implements CommandLineRunner{
	
	@Autowired
	private ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProductMsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Product p1 = new Product(null, "Moto G6 Plus", "D1", new BigDecimal(1259.0));
		Product p2 = new Product(null, "J5 Pro", "D1", new BigDecimal(1099.0));
		Product p3 = new Product(null, "Moto X4", "D2", new BigDecimal(1700.0));
		Product p4 = new Product(null, "Lg Q6", "D2", new BigDecimal(2500.0));
		Product p5 = new Product(null, "Moto G5s", "D3", new BigDecimal(800.0));
		Product p6 = new Product(null, "J7 Pro", "D3", new BigDecimal(1300.0));
		Product p7 = new Product(null, "Asus Zenfone", "D4", new BigDecimal(700.0));
		Product p8 = new Product(null, "Moto G6", "D4", new BigDecimal(1850.0));
		
		productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8));
		
	}

}
