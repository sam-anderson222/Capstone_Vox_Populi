package com.example.VoxPopuli.services;

import com.example.VoxPopuli.model.User;
import com.example.VoxPopuli.model.UserLogInSignUpAttempt;
import com.example.VoxPopuli.repository.UserRepository;
import com.example.VoxPopuli.repository.exceptions.DatabaseErrorException;
import com.example.VoxPopuli.repository.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Service
@Primary
public class UserService {
    @Autowired
    private UserRepository userRepo;

    public UserService(){}

    public List<User> getAllUsers() {
        return userRepo.getAllUsers();
    }

    public Optional<User> getUserById(Integer userId) {
        return userRepo.getUserById(userId);
    }

    public Optional<User> logIn(UserLogInSignUpAttempt logInInfo) {
        if (logInInfo.getUserPassword().length() < 4 || logInInfo.getUsername().length() < 4) {
            return Optional.empty();
        }

        return userRepo.logIn(logInInfo);
    }

    public boolean registerUser(UserLogInSignUpAttempt signUpInfo) {
        if (signUpInfo.getUserPassword().length() < 4 || signUpInfo.getUsername().length() < 4) {
            return false;
        }

       return userRepo.registerUser(signUpInfo);
    }
}
