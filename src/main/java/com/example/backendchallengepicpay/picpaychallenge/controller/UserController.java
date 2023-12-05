package com.example.backendchallengepicpay.picpaychallenge.controller;

import com.example.backendchallengepicpay.picpaychallenge.domain.user.User;
import com.example.backendchallengepicpay.picpaychallenge.dto.UserDTO;
import com.example.backendchallengepicpay.picpaychallenge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDTO user) {
        User newUser = userService.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity removeAllUsers() {
        userService.removeAllUsers();
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
