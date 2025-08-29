package com.gtelant.commerce_servise.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 50, name = "first_name")
    private String firstName;
    @Column(nullable = false, length = 50, name = "last_name")
    private String lastName;
    @Column(nullable = false, length = 255, name = "email",unique = true)
    private String email;
    @Column(name = "birthday")
    private LocalDate birthday;
    @Column(name = "address", length = 255)
    private String address;
    @Column(name = "city", length = 100)
    private String city;
    @Column(name = "state", length = 100)
    private String state;
    @Column(name = "zipcode", length = 20)
    private String zipcode;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password", nullable = false, length = 255)
    private String password;
    @Column(name = "has_newsletter",nullable = false)
    private boolean hasNewsletter;
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
    @Column(name = "role")
    private String role;

    @OneToMany (mappedBy = "user",cascade =CascadeType.ALL,fetch = FetchType.LAZY)
    private List<UserSegment> userSagments;


}
