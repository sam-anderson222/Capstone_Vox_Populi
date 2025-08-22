package com.example.VoxPopuli.model;

// Holds data from a user login attempt (only username and password)
// Is used to pass into the UserRepo to attempt a login.
public class UserLogInSignUpAttempt {
    private String username;
    private String userPassword;

    // Default Constructor
    public UserLogInSignUpAttempt() {}

    // Constructor with parameters
    public UserLogInSignUpAttempt(String username, String userPassword) {
        this.username = username;
        this.userPassword = userPassword;
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getUserPassword() {
        return userPassword;
    }



    // Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }


    @Override
    public String toString() {
        return String.format("UserLogInAttempt{username=%s, user_password=%s}", username, userPassword);
    }
}
