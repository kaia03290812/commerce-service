package com.gtelant.commerce_servise.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String address;
    private String city;
    private String zipcode;
    private String state;
}
