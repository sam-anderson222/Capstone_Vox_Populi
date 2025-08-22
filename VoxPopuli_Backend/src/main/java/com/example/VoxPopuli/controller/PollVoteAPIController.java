package com.example.VoxPopuli.controller;

import com.example.VoxPopuli.model.*;
import com.example.VoxPopuli.repository.PollOptionRepository;
import com.example.VoxPopuli.repository.PollRepository;
import com.example.VoxPopuli.repository.PollVoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pollVotes")
public class PollVoteAPIController {

    @Autowired
    PollVoteRepository pollVoteRepo;

    @GetMapping("/{id}")
    public ResponseEntity<List<PollOptionVoteResult>> getPollResults(@PathVariable Integer id) {
        List<PollOptionVoteResult> pollResults = pollVoteRepo.getResultsForPoll(id);

        return ResponseEntity.ok(pollResults);
    }

    @PostMapping("/hasVoted/{id}")
    public ResponseEntity<PollVoteWithOption> hasUserVotedOnPoll(@PathVariable Integer id, @RequestBody User user) {
        Optional<PollVoteWithOption> userVote= pollVoteRepo.userVotedOnPoll(user, id);

        return userVote.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NO_CONTENT).build());
    }


    @PostMapping("/vote")
    public ResponseEntity<Boolean> voteOnPoll(@RequestBody PollVote pollVote) {
        boolean successfullyVoted = pollVoteRepo.voteOnPoll(pollVote);
        System.out.println("Attempts to vote: " + pollVote);

        if (successfullyVoted) {
            return ResponseEntity.ok(Boolean.TRUE);
        } else {
            // If there is an error on the DB side for submitting the user's vote, then return this.
            System.out.println("Error, invalid vote");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
