package com.gtelant.commerce_servise.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    @Column(name = "name", nullable = false, length = 120)
    private String name;
    @Column(name = "width", precision = 10, scale = 2)
    private BigDecimal width;
    @PositiveOrZero
    @Column(name = "height", precision = 10, scale = 2)
    private BigDecimal height;
    @PositiveOrZero
    @Column(name = "price", nullable = false, precision = 12, scale = 2)
    private BigDecimal price;
    @PositiveOrZero
    @Column(name = "stock", nullable = false)
    private Integer stock = 0;
    @PositiveOrZero
    @Column(name = "sales", nullable = false)
    private Integer sales = 0;
    @Size(max = 2000)
    @Column(name = "description", length = 2000)
    private String description;
    @Column(name = "image_url", length = 500)
    private String imageUrl;
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
