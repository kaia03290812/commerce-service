package com.gtelant.commerce_servise;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.gtelant.commerce_servise.model")
@EnableJpaRepositories(basePackages = "com.gtelant.commerce_servise.repositories")
@OpenAPIDefinition
public class CommerceServiseApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommerceServiseApplication.class, args);
	}

}
