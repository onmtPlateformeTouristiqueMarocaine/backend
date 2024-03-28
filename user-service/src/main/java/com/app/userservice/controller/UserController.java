package com.app.userservice.controller;

import com.app.userservice.dto.UserRequest;
import com.app.userservice.dto.UserResponse;
import com.app.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final UserService userService;



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createuser(@RequestBody UserRequest userRequest){
        userService.createUser(userRequest);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long username) {
        System.out.println(username);
        Optional<UserResponse> userResponseOptional = userService.getUserById(username);
        if (userResponseOptional.isPresent()) {
            return ResponseEntity.ok(userResponseOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

