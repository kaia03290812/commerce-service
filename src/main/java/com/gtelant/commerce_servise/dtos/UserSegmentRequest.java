package com.gtelant.commerce_servise.dtos;

import lombok.Data;

@Data
public class UserSegmentRequest {
    private int userId;
    private int segmentId;
}
