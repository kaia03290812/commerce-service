package com.gtelant.commerce_servise.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "segmanets")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Segmanet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "created_at", updatable = false)
    private LocalDateTime created_at;
    @Column(name = "deleted_at")
    private LocalDateTime deleted_at;
    @OneToMany(mappedBy = "segmanet", fetch = FetchType.LAZY)
    private List<UserSegment> userSegments;
}

