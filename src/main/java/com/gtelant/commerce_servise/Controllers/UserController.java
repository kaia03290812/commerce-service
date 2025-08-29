package com.gtelant.commerce_servise.Controllers;

import com.gtelant.commerce_servise.model.User;
import com.gtelant.commerce_servise.repositories.UserRepository;
import com.gtelant.commerce_servise.requests.CreateUserRequest;
import com.gtelant.commerce_servise.requests.UpdateUserRequest;
import com.gtelant.commerce_servise.responses.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @GetMapping
    @Operation(summary = "取得所有用戶",security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponse> responseList = users.stream()
                .map(UserResponse::new)
                .toList();
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{id}")
    @Operation(summary = "取得單一用戶",security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<UserResponse> getUserById(@PathVariable int id) {
        return userRepository.findById(id)
                .map(user -> ResponseEntity.ok(new UserResponse(user)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "新增用戶",security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("message", "使用者名稱已存在"));
        }
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setBirthday(request.getBirthday());
        user.setAddress(request.getAddress());
        user.setCity(request.getCity());
        user.setZipcode(request.getZipcode());
        user.setState(request.getState());
        user.setHasNewsletter(Boolean.TRUE.equals(request.getHasNewsletter()));
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("ROLE_USER");
        User savedUser = userRepository.save(user);
        return ResponseEntity.status(201).body(new UserResponse(savedUser));
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新用戶資料",security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<UserResponse> updateUserById(@PathVariable Integer id, @RequestBody UpdateUserRequest request) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        User user = optionalUser.get();
        if (request.getFirstName() != null) user.setFirstName(request.getFirstName());
        if (request.getLastName() != null) user.setLastName(request.getLastName());
        if (request.getCity() != null) user.setCity(request.getCity());
        if (request.getBirthday() != null) user.setBirthday(request.getBirthday());
        if (request.getAddress() != null) user.setAddress(request.getAddress());
        if (request.getState() != null) user.setState(request.getState());
        if (request.getZipcode() != null) user.setZipcode(request.getZipcode());
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(new UserResponse(savedUser));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "刪除用戶",security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Void> deleteUserById(@PathVariable int id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userRepository.delete(optionalUser.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
