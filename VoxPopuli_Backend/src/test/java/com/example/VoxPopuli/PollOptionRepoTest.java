package com.example.VoxPopuli;

import com.example.VoxPopuli.model.PollOption;
import com.example.VoxPopuli.model.User;
import com.example.VoxPopuli.model.UserLogInSignUpAttempt;
import com.example.VoxPopuli.repository.PollOptionRepository;
import com.example.VoxPopuli.repository.UserRepository;
import com.example.VoxPopuli.repository.exceptions.DatabaseErrorException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PollOptionRepoTest {
    @Autowired
    private PollOptionRepository pollOptionRepo;


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
    void getAllPollOptions_returnsAllPollOptions() {
        List<PollOption> options = pollOptionRepo.getAllPollOptions();
        int numberOfOptions = 21;

        assertEquals(numberOfOptions, options.size());
    }

    @Test
    void getAllPollOptionsForPoll_validPollId_returnsAllPollOptions() {
        List<PollOption> options = pollOptionRepo.getAllPollOptionsForPoll(1);
        int numberOfOptions = 2;

        assertEquals(numberOfOptions, options.size());
    }

    @Test
    void getAllPollOptionsForPoll_invalidPollId_returnsError() {
        assertThrows(DatabaseErrorException.class, () -> {
            pollOptionRepo.getAllPollOptionsForPoll(-1);
        });
    }

    @Test
    void saveOptionsForPoll_validData_returnsTrue() {
        List<PollOption> newOptions = List.of(new PollOption(0, 3, "Fanta"), new PollOption(0, 4, "Dr.Pepper"));
        boolean isSuccess = pollOptionRepo.saveOptionsForPoll(1, newOptions); // Add two more options to poll #1.

        List<PollOption> newOptionsForPollOne = pollOptionRepo.getAllPollOptionsForPoll(1);
        int numberOfOptions = 4;

        assertTrue(isSuccess);
        assertEquals(numberOfOptions, newOptionsForPollOne.size());
    }

    @Test
    void saveOptionsForPoll_invalidData_returnsFalse() {
        // Option numbers are invalid as they must be unique alongside optionName.
        List<PollOption> newOptions = List.of(new PollOption(0, 1, "Fanta"), new PollOption(0, 1, "Dr.Pepper"));
        boolean isSuccess = pollOptionRepo.saveOptionsForPoll(1, newOptions); // Add two more options to poll #1.

        assertFalse(isSuccess);
    }


}
