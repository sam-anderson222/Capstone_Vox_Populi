package com.example.VoxPopuli.controller;

import com.example.VoxPopuli.model.Poll;
import com.example.VoxPopuli.model.PollOverview;
import com.example.VoxPopuli.model.PollVote;
import com.example.VoxPopuli.repository.PollOptionRepository;
import com.example.VoxPopuli.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/polls")
public class PollAPIController {

    @Autowired
    PollRepository pollRepo;

    @Autowired
    PollOptionRepository pollOptionRepo;

    @GetMapping
    public ResponseEntity<List<Poll>> getAllPolls() {
        List<Poll> polls = pollRepo.getAllPolls();
        System.out.println("All polls gotten!");
        return ResponseEntity.ok(polls);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Poll> getPollById(@PathVariable Integer id) {
        Optional<Poll> poll = pollRepo.getPollById(id);
        return poll.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/overviews")
    public ResponseEntity<List<PollOverview>> getAllPollOverviews() {
        List<PollOverview> polls = pollRepo.getAllPollOverviews();
        System.out.println("All polls overviews gotten!");
        return ResponseEntity.ok(polls);
    }

    @PostMapping
    public ResponseEntity<Boolean> createPoll(@RequestBody Poll newPoll) {
        boolean isSuccess = pollRepo.savePoll(newPoll);

        if (isSuccess) {
            return ResponseEntity.ok(Boolean.TRUE);
        } else {
            // If there is an error on the DB side for submitting the user's vote, then return this.
            System.out.println("Error, could not add poll");
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
