package com.gtelant.commerce_servise.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SegmentResponse {
    private int id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
}
