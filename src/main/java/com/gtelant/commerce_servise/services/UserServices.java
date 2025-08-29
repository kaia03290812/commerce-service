package com.gtelant.commerce_servise.services;

import com.gtelant.commerce_servise.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServices {
    private final UserRepository userRepository;

    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}

