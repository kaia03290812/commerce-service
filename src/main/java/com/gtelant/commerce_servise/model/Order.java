package com.gtelant.commerce_servise.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "order_number", nullable = false, unique = true, length = 100)
    private String orderNumber;
    @Column(name = "subtotal", precision = 12, scale = 2)
    private BigDecimal subtotal;
    @Column(name = "tax", precision = 12, scale = 2)
    private BigDecimal tax;
    @Column(name = "shipping_fee", precision = 12, scale = 2)
    private BigDecimal shippingFee;
    @Column(name = "discount", precision = 12, scale = 2)
    private BigDecimal discount;
    @Column(name = "total", precision = 12, scale = 2, nullable = false)
    private BigDecimal total;
    @Column(name = "paid_at")
    private LocalDateTime paidAt;
    @Column(name = "shipped_at")
    private LocalDateTime shippedAt;
    @Column(name = "delivered_at")
    private LocalDateTime deliveredAt;
    @Column(name = "remarks", length = 1000)
    private String remarks;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private Status status = Status.PENDING;

    @PrePersist
    public void generateOrderNumber() {
        if (orderNumber == null) {
            orderNumber = "ORD" + System.currentTimeMillis();
        }
    }

    public enum Status {
        PENDING,    // 建立訂單、未付款
        PAID,       // 已付款
        SHIPPED,    // 已出貨
        DELIVERED,  // 已送達
        CANCELLED,  // 已取消
        REFUNDED    // 已退款
    }
}
