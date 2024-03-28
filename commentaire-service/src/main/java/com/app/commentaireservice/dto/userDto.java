package com.app.commentaireservice.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class userDto {
    private Long id;
    private String username;
    private String lastName;
    private LocalDate dateOfBirth;
    private String email;
    private String password;
    private String phoneNumber;
}
