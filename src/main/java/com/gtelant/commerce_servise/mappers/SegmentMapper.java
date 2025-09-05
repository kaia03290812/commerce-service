package com.gtelant.commerce_servise.mappers;

import com.gtelant.commerce_servise.dtos.SegmentRequest;
import com.gtelant.commerce_servise.dtos.SegmentResponse;
import com.gtelant.commerce_servise.model.Segment;
import org.springframework.stereotype.Component;

@Component
public class SegmentMapper {
    public SegmentResponse toResponse(Segment segment) {
        SegmentResponse response = new SegmentResponse();
        response.setId(segment.getId());
        response.setName(segment.getName());
        response.setCreatedAt(segment.getCreated_at());
        response.setDeletedAt(segment.getDeleted_at());
        return response;
    }

    public Segment toEntity(SegmentRequest request) {
        Segment segment = new Segment();
        segment.setName(request.getName());
        return segment;
    }

    public Segment updateEntity(Segment segment, SegmentRequest request) {
        if (request.getName() != null) {
            segment.setName(request.getName());
        }
        return segment;
    }
}
