package com.gtelant.commerce_servise.services;

import com.gtelant.commerce_servise.model.Segment;
import com.gtelant.commerce_servise.repositories.SegmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SegmentService {
    private final SegmentRepository segmentRepository;

    public List<Segment> getAllSegments() {
        return segmentRepository.findAll();
    }

    public Optional<Segment> getSegmentById(int id) {
        return segmentRepository.findById(id);
    }

    public Segment saveSegment(Segment segment) {
        return segmentRepository.save(segment);
    }

    public void deleteSegment(int id) {
        segmentRepository.deleteById(id);
    }
}
