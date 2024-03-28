package com.app.userservice.controller;

import com.app.userservice.dto.UserRequest;
import com.app.userservice.model.User;
import com.app.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserRequest userRequest) {
        User user = userService.authenticateUser(userRequest.getEmail(), userRequest.getPassword());
        if (user != null) {
            return ResponseEntity.ok().body("Login successful");
        } else {
            return ResponseEntity.ok().body("Authentication failed");
        }
    }
}
