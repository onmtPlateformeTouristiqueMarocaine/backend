package com.app.userservice.service;

import com.app.userservice.dto.UserRequest;
import com.app.userservice.dto.UserResponse;
import com.app.userservice.model.User;
import com.app.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public void createUser(UserRequest userRequest) {
        User user = User.builder()

                .username(userRequest.getUsername())
                .lastName(userRequest.getLastName())
                .dateOfBirth(userRequest.getDateOfBirth())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .phoneNumber(userRequest.getPhoneNumber())
                .build();
        userRepository.save(user);
        log.info("User is saved");
    }



    public Optional<UserResponse> getUserByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        return userOptional.map(this::mapToUserResponse);
    }
    private UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .lastName(user.getLastName())
                .dateOfBirth(user.getDateOfBirth())
                .email(user.getEmail())
                .password(user.getPassword())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }

    public Optional<UserResponse> getUserById(Long username) {
        Optional<User> userOptional = userRepository.findById(username);
        return userOptional.map(this::mapToUserResponse);
    }


    public User authenticateUser(String email, String password) {
        log.info("Authenticating user with email: {}", email);
        User user = userRepository.findByEmail(email);
        if (user != null) {
            log.info("User found with email: {}", email);
            if (passwordEncoder.matches(password, user.getPassword())) {
                log.info("Password matches for user with email: {}", email);
                return user;
            } else {
                log.info("Password does not match for user with email: {}", email);
            }
        } else {
            log.info("User not found with email: {}", email);
        }
        return null;
    }

}
