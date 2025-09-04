package com.gtelant.commerce_servise.Controllers;

import com.gtelant.commerce_servise.dtos.UserSegmentResponse;
import com.gtelant.commerce_servise.mappers.UserSegmentMapper;
import com.gtelant.commerce_servise.model.UserSegment;
import com.gtelant.commerce_servise.services.UserSegmentService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user-segments")
@CrossOrigin("*")
public class UserSegmentContorller {
    private final UserSegmentService userSegmentService;
    private final UserSegmentMapper userSegmentMapper;

    @Autowired
    public UserSegmentContorller(UserSegmentService userSegmentService, UserSegmentMapper userSegmentMapper) {
        this.userSegmentService = userSegmentService;
        this.userSegmentMapper = userSegmentMapper;
    }

    @Operation(summary = "取得所有UserSegment列表", description = "")
    @GetMapping
    public ResponseEntity<List<UserSegmentResponse>> getAllUserSegments() {
        List<UserSegment> userSegments = userSegmentService.getAllUserSegments();
        List<UserSegmentResponse> response = userSegments.stream()
                .map(userSegmentMapper::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }
}
