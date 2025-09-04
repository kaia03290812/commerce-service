package com.gtelant.commerce_servise.dtos;

import lombok.Data;

@Data
public class UserSegmentResponse {
    private int id;
    private int userId;
    private int segmentId;
    private String name;
}
