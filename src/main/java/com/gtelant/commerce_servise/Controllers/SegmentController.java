package com.gtelant.commerce_servise.Controllers;

import com.gtelant.commerce_servise.dtos.SegmentRequest;
import com.gtelant.commerce_servise.dtos.SegmentResponse;
import com.gtelant.commerce_servise.mappers.SegmentMapper;
import com.gtelant.commerce_servise.model.Segment;
import com.gtelant.commerce_servise.services.SegmentService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/segments")
@CrossOrigin("*")
public class SegmentController {
    private final SegmentService segmentService;
    private final SegmentMapper segmentMapper;

    @Autowired
    public SegmentController(SegmentService segmentService, SegmentMapper segmentMapper) {
        this.segmentService = segmentService;
        this.segmentMapper = segmentMapper;
    }

    @Operation(summary = "取得所有Segment列表", description = "")
    @GetMapping
    public ResponseEntity<List<SegmentResponse>> getAllSegments() {
        List<Segment> segments = segmentService.getAllSegments();
        List<SegmentResponse> response = segments.stream()
                .map(segmentMapper::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "新增Segment", description = "")
    @PostMapping
    public ResponseEntity<SegmentResponse> createSegment(@RequestBody SegmentRequest request) {
        Segment segment = segmentMapper.toEntity(request);
        Segment savedSegment = segmentService.saveSegment(segment);
        return ResponseEntity.ok(segmentMapper.toResponse(savedSegment));
    }

    @Operation(summary = "修改Segment", description = "")
    @PutMapping("/{id}")
    public ResponseEntity<SegmentResponse> updateSegment(@PathVariable int id, @RequestBody SegmentRequest request) {
        Optional<Segment> segment = segmentService.getSegmentById(id);
        if (segment.isPresent()) {
            Segment updatedSegment = segmentMapper.updateEntity(segment.get(), request);
            Segment savedSegment = segmentService.saveSegment(updatedSegment);
            return ResponseEntity.ok(segmentMapper.toResponse(savedSegment));
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "刪除指定Segment", description = "")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSegment(@PathVariable int id) {
        Optional<Segment> segment = segmentService.getSegmentById(id);
        if (segment.isPresent()) {
            segmentService.deleteSegment(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}