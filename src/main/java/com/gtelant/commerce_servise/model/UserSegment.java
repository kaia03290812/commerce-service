package com.gtelant.commerce_servise.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_segments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSegment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "segmanet_id")
    private Segmanet segmanet;
    @Column(name = "created_at", updatable = false)
    private LocalDateTime created_at;
    @Column(name = "deleted_at")
    private LocalDateTime deleted_at;

}
