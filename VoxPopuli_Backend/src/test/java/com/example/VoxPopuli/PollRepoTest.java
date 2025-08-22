package com.example.VoxPopuli;

import com.example.VoxPopuli.model.*;
import com.example.VoxPopuli.repository.PollRepository;
import com.example.VoxPopuli.repository.PollVoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PollRepoTest {
    @Autowired
    private PollRepository pollRepo;


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
    void getAllPolls_returnsAllPolls() {
        List<Poll> polls = pollRepo.getAllPolls();
        int numberOfPolls = 5;

        assertEquals(numberOfPolls, polls.size());
    }

    @Test
    void getAllPollOverviews_returnsAllPollOverviews() {
        List<PollOverview> polls = pollRepo.getAllPollOverviews();
        int numberOfPolls = 5;

        assertEquals(numberOfPolls, polls.size());
    }

    @Test
    void getPollById_validId_returnsPoll() {
        Optional<Poll> p = pollRepo.getPollById(1);

        assertTrue(p.isPresent());
    }

    @Test
    void getPollById_invalidId_returnsEmpty() {
        Optional<Poll> p = pollRepo.getPollById(-1);

        assertTrue(p.isEmpty());
    }

    @Test
    void savePoll_validPoll_returnsTrue() {
        Poll newPoll = new Poll(0, 1, "Test Poll", "Desc", List.of(new PollOption(0, 1, "1"), new PollOption(0, 2, "2")));
        boolean isSuccess = pollRepo.savePoll(newPoll);

        List<Poll> polls = pollRepo.getAllPolls();
        int numberOfPolls = 6;


        assertTrue(isSuccess);
        assertEquals(numberOfPolls, polls.size());
    }

    @Test
    void savePoll_invalidPoll_returnsFalse() {
        // Option number are duplicated, which is invalid.
        Poll newPoll = new Poll(0, 1, "Test Poll", "Desc", List.of(new PollOption(0, 1, "1"), new PollOption(0, 1, "1")));
        boolean isSuccess = pollRepo.savePoll(newPoll);


        assertFalse(isSuccess);
    }

    @Test
    void deletePoll_validId_returnsTrue() {
        boolean isSuccess = pollRepo.deletePollById(1);

        List<Poll> polls = pollRepo.getAllPolls();
        int numberOfPolls = 4;


        assertTrue(isSuccess);
        assertEquals(numberOfPolls, polls.size());
    }




}
