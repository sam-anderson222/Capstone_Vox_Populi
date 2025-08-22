package com.example.VoxPopuli;

import com.example.VoxPopuli.model.User;
import com.example.VoxPopuli.model.UserLogInSignUpAttempt;
import com.example.VoxPopuli.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepoTest {
    @Autowired
    private UserRepository userRepo;


    @Autowired
    private JdbcTemplate jdbcTemplate; // Used to access and reset the sql database.

    @BeforeEach
    void setUp() {
        try {
            jdbcTemplate.execute("{CALL set_known_good_state()}");
        } catch (Exception ex) {
            System.out.println("Error");
        }
    }

    @Test
    void getAllUsers_returnsAllUsers() {
        List<User> users = userRepo.getAllUsers();
        int numberOfUsers = 18;

        assertEquals(numberOfUsers, users.size());
    }

    @Test
    void getUserById_validId_returnsUser() {
        Optional<User> user = userRepo.getUserById(1);
        User expectedUser = new User(1, "TestUserA", "TestPassword1", 2);

        assertTrue(user.isPresent());
        assertEquals(expectedUser.toString(), user.get().toString());
    }

    @Test
    void getUserById_invalidId_returnsEmpty() {
        Optional<User> user = userRepo.getUserById(-1);

        assertTrue(user.isEmpty());
    }

    @Test
    void logIn_validInfo_returnsUser() {
        Optional<User> user = userRepo.logIn(new UserLogInSignUpAttempt("TestUserA", "TestPassword1"));
        User expectedUser = new User(1, "TestUserA", "TestPassword1", 2);

        assertTrue(user.isPresent());
        assertEquals(expectedUser.toString(), user.get().toString());
    }

    @Test
    void logIn_invalidInfo_returnsEmpty() {
        Optional<User> user = userRepo.logIn(new UserLogInSignUpAttempt("TestUserA", "testpassword1"));

        assertTrue(user.isEmpty());
    }

    @Test
    void registerUser_validInfo_returnsTrue() {
        boolean successfullyRegistered = userRepo.registerUser(new UserLogInSignUpAttempt("NewAccount", "Password"));

        assertTrue(successfullyRegistered);
    }

    @Test
    void registerUser_nonUniqueUsername_returnsFalse() {
        boolean successfullyRegistered = userRepo.registerUser(new UserLogInSignUpAttempt("TestUserA", "Password"));

        assertFalse(successfullyRegistered);
    }
}
