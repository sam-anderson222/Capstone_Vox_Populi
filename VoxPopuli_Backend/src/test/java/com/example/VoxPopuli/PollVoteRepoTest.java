package com.example.VoxPopuli;

import com.example.VoxPopuli.model.*;
import com.example.VoxPopuli.repository.PollOptionRepository;
import com.example.VoxPopuli.repository.PollVoteRepository;
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
class PollVoteRepoTest {
    @Autowired
    private PollVoteRepository pollVoteRepo;


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
    void voteOnPoll_validVote_returnsTrue() {
        PollVote vote = new PollVote(18, 1, 1);
        boolean isSuccess = pollVoteRepo.voteOnPoll(vote);


        assertTrue(isSuccess);
    }

    @Test
    void voteOnPoll_invalidVote_returnsFalse() {
        PollVote vote = new PollVote(1, 1, 1); // User one has already vote on this poll.
        boolean isSuccess = pollVoteRepo.voteOnPoll(vote);


        assertFalse(isSuccess);
    }

    @Test
    void userVotedOnPoll_validInfo_returnsUsersPollVote() {
        User u = new User(1, "TestUserA", "TestPassword1", 2);

        Optional<PollVoteWithOption> userVote = pollVoteRepo.userVotedOnPoll(u, 1);
        PollVoteWithOption pv = new PollVoteWithOption(1, 1, 1, "Coke");



        assertTrue(userVote.isPresent());
        assertEquals(pv.toString(), userVote.get().toString());
    }

    @Test
    void userVotedOnPoll_invalidInfo_returnsEmpty() {
        User u = new User(1, "TestUserA", "TestPassword1", 2);
        Optional<PollVoteWithOption> userVote = pollVoteRepo.userVotedOnPoll(u, -1); // Poll ID -1 is invalid.

        assertTrue(userVote.isEmpty());
    }

    @Test
    void getResultsForPoll_validPollId_returnsResults() {
        List<PollOptionVoteResult> results = pollVoteRepo.getResultsForPoll(1);
        int numberOfResults = 2;



        assertEquals(numberOfResults, results.size());
    }
}
