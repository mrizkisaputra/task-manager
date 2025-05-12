package com.taskmanager.controllers;

import com.taskmanager.dto.CreateUserRequest;
import com.taskmanager.exceptions.EmailAlreadyUsedException;
import com.taskmanager.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Void> handleRegister(@RequestBody @Valid CreateUserRequest newCreateUserRequest)
            throws EmailAlreadyUsedException {

        userService.register(newCreateUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
