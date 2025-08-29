package com.gtelant.commerce_servise.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {
    @NotBlank
    @Size(max = 50)
    private String firstName;
    @NotBlank
    @Size(max = 50)
    private String lastName;
    @NotBlank
    @Email
    @Size(max = 255)
    private String email;
    // ISO-8601 (yyyy-MM-dd)
    private LocalDate birthday;
    @Size(max = 255)
    private String address;
    @Size(max = 100)
    private String city;
    @Size(max = 100)
    private String state;
    @Size(max = 20)
    private String zipcode;
    @NotBlank
    @Size(min = 6, max = 255)
    private String password;
    @NotNull
    private Boolean hasNewsletter = false;
    @Size(max = 50)
    private String role = "ROLE_USER";
}
