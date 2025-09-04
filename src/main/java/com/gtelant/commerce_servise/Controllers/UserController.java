package com.gtelant.commerce_servise.Controllers;

import com.gtelant.commerce_servise.dtos.UserRequest;
import com.gtelant.commerce_servise.dtos.UserResponse;
import com.gtelant.commerce_servise.mappers.UserMapper;
import com.gtelant.commerce_servise.mappers.UserSegmentMapper;
import com.gtelant.commerce_servise.model.Segment;
import com.gtelant.commerce_servise.model.User;
import com.gtelant.commerce_servise.model.UserSegment;
import com.gtelant.commerce_servise.services.SegmentService;
import com.gtelant.commerce_servise.services.UserSegmentService;
import com.gtelant.commerce_servise.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final SegmentService segmentService;
    private final UserSegmentMapper userSegmentMapper;
    private final UserSegmentService userSegmentService;

    public UserController(UserService userService, UserMapper userMapper, SegmentService segmentService, UserSegmentMapper userSegmentMapper, UserSegmentService userSegmentService) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.segmentService = segmentService;
        this.userSegmentMapper = userSegmentMapper;
        this.userSegmentService = userSegmentService;
    }

    @Operation(summary = "取得所有用戶", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserResponse> response = users.stream()
                .map(userMapper::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "取得使用者列表（分頁＋搜尋＋篩選）")
    @GetMapping("/page")
    public ResponseEntity<Page<UserResponse>> getAllUsersPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false, name = "query") String query,
            @RequestParam(required = false, name = "hasnewsletter") Boolean hasNewsletter,
            @RequestParam(required = false, name = "segmentId") Integer segmentId
    ) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<User> users = userService.getAllUsers(query, hasNewsletter, segmentId, pageRequest);
        return ResponseEntity.ok(users.map(userMapper::toResponse));
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable int id) {
        return userService.getUserById(id)
                .map(u -> ResponseEntity.ok(userMapper.toResponse(u)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "新增用戶", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request) {
        User user = userMapper.toEntity(request);
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(userMapper.toResponse(savedUser));
    }

    @Operation(summary = "更新用戶資料", security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable int id, @RequestBody UserRequest request) {
        return userService.getUserById(id)
                .map(user -> {
                    User updated = userMapper.updateEntity(user, request);
                    User saved = userService.saveUser(updated);
                    return ResponseEntity.ok(userMapper.toResponse(saved));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "刪除用戶", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/segments/{segmentId}")
    public ResponseEntity<Void> assignSegmentToUser(@PathVariable int id, @PathVariable int segmentId) {
        Optional<User> user = userService.getUserById(id);
        Optional<Segment> segment = segmentService.getSegmentById(segmentId);
        if (user.isPresent() && segment.isPresent()) {
            UserSegment usersegment = userSegmentMapper.toEntity(user.get(), segment.get());
            UserSegment savedUserSegment = userSegmentService.saveUserSegment(usersegment);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}

