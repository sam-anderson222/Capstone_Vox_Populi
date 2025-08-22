package com.example.VoxPopuli.controller;

import com.example.VoxPopuli.model.User;
import com.example.VoxPopuli.model.UserLogInSignUpAttempt;
import com.example.VoxPopuli.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserAPIController {

    @Autowired
    UserRepository userRepo;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepo.getAllUsers();
        System.out.println("All users gotten!");
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        Optional<User> user = userRepo.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/logIn")
    public ResponseEntity<User> logIn(@RequestBody UserLogInSignUpAttempt logInInfo) {
        Optional<User> user = userRepo.logIn(logInInfo);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @PostMapping("/signUp")
    public ResponseEntity<Boolean> signUp(@RequestBody UserLogInSignUpAttempt signUpInfo) {
        boolean isSuccess = userRepo.registerUser(signUpInfo);

        if (isSuccess) {
            return ResponseEntity.ok(Boolean.TRUE);
        } else {
            // If there is an error on the DB side for submitting the user's vote, then return this.
            System.out.println("Error, could not register user");
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
