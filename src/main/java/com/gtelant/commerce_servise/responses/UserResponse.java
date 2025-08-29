package com.gtelant.commerce_servise.responses;

import com.gtelant.commerce_servise.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthday;
    private String address;
    private String city;
    private String state;
    private String zipcode;
    private boolean hasNewsletter;
    private String role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;



    public UserResponse(User user) {
         this.id = user.getId();
         this.firstName=user.getFirstName();
         this.lastName=user.getLastName();
         this.email=user.getEmail();
         this.birthday=user.getBirthday();
         this.address=user.getAddress();
         this.city=user.getCity();
         this.state=user.getState();
         this.zipcode=user.getZipcode();
         this.hasNewsletter=user.isHasNewsletter();
         this.role=user.getRole();
         this.createdAt=user.getCreatedAt();
         this.updatedAt=user.getUpdatedAt();
         this.deletedAt=user.getDeletedAt();
    }
}

