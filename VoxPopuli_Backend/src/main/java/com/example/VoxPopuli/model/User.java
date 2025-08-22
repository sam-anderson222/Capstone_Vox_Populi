package com.example.VoxPopuli.model;

public class User {
    private Integer userId;
    private String username;
    private String userPassword;
    private Integer accessLevel;

    // Default Constructor
    public User() {}

    // Constructor with parameters
    public User(Integer userId, String username, String userPassword, Integer accessLevel) {
        this.userId = userId;
        this.username = username;
        this.userPassword = userPassword;
        this.accessLevel = accessLevel;
    }

    // Getters
    public Integer getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public Integer getAccessLevel() {
        return accessLevel;
    }

    // Setters
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setAccessLevel(Integer accessLevel) {
        this.accessLevel = accessLevel;
    }

    @Override
    public String toString() {
        return String.format("User{userId=%d, username=%s, user_password=%s, access_level=%d}", userId, username, userPassword, accessLevel);
    }
}
