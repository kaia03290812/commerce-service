package com.gtelant.commerce_servise.repositories;

import com.gtelant.commerce_servise.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
