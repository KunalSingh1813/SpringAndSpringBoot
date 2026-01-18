package com.JpaTutorial.JpaTutorial;

import com.JpaTutorial.JpaTutorial.entities.ProductEntity;
import com.JpaTutorial.JpaTutorial.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class JpaTutorialApplicationTests {

	@Autowired
	ProductRepository  productRepository;
	@Test
	void contextLoads() {
	}

	@Test
	void testRepository(){
		ProductEntity productEntity = ProductEntity.builder()
				.sku("nestleChocolate")
				.title("Nestle Chocolate")
				.price(BigDecimal.valueOf(123.45))
				.quantity(12)
				.build();

		ProductEntity savedProductEntity = productRepository.save(productEntity);
		System.out.println(savedProductEntity);
	}

	@Test
	void getRepository(){
//		List<ProductEntity> entities = productRepository.findByCreatedAtAfter(
//				LocalDateTime.of(2024,1,1,0,0,0));

//		List<ProductEntity> entities = productRepository.findByQuantityGreaterThanAndPriceLessThan(2, BigDecimal.valueOf(30));

		List<ProductEntity> entities = productRepository.findByTitleContainingIgnoreCase("Choco");
		System.out.println(entities);
	}

	@Test
	void getSingleFromRepository(){
		Optional<String> productEntity = productRepository.findByTitleAndPrice("Nestle Chocolate", BigDecimal.valueOf(123.45));
		productEntity.ifPresent(System.out::println);
	}


}
