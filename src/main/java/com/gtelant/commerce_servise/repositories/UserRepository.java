package com.gtelant.commerce_servise.repositories;

import com.gtelant.commerce_servise.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);



}
