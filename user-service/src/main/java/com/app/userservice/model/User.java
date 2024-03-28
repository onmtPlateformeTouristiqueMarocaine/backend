package com.app.userservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Data
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String lastName;
    private LocalDate dateOfBirth;
    private String email;
    private String password;
    private String phoneNumber;

    public User() {

    }

    public User(Long id, String username, String lastName, LocalDate dateOfBirth, String email, String password, String phoneNumber) {
        this.id = id;
        this.username = username;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }
}
