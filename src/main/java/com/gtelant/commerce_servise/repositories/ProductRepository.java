package com.gtelant.commerce_servise.repositories;

import com.gtelant.commerce_servise.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
