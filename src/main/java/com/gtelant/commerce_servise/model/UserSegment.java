package com.gtelant.commerce_servise.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "user_segments")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserSegment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne(fetch = LAZY, optional = false)
    @JoinColumn(name = "segment_id", nullable = false)
    private Segment segment;
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime created_at;
    @Column(name = "deleted_at")
    private LocalDateTime deleted_at;
}
