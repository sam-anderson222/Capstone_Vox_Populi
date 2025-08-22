package com.example.VoxPopuli.repository;

import com.example.VoxPopuli.model.User;
import com.example.VoxPopuli.model.UserLogInSignUpAttempt;
import com.example.VoxPopuli.repository.exceptions.DatabaseErrorException;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> getAllUsers() throws DatabaseErrorException;
    Optional<User> getUserById(Integer userId) throws DatabaseErrorException;
    Optional<User> logIn (UserLogInSignUpAttempt logInInfo);
    boolean registerUser(UserLogInSignUpAttempt user);
}
