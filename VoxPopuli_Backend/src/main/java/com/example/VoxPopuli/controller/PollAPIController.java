package com.example.VoxPopuli.controller;

import com.example.VoxPopuli.model.Poll;
import com.example.VoxPopuli.model.PollOverview;
import com.example.VoxPopuli.services.PollService;
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
    PollService pollService;

    @GetMapping
    public ResponseEntity<List<Poll>> getAllPolls() {
        List<Poll> polls = pollService.getAllPolls();
        System.out.println("All polls gotten!");
        return ResponseEntity.ok(polls);
    }

    @GetMapping("/number")
    public ResponseEntity<Integer> getNumberOfPolls() {
        Integer numberOfPolls = pollService.getNumberOfPolls();
        return ResponseEntity.ok(numberOfPolls);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Poll> getPollById(@PathVariable Integer id) {
        Optional<Poll> poll = pollService.getPollById(id);
        return poll.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/overviews")
    public ResponseEntity<List<PollOverview>> getAllPollOverviews(@RequestBody Integer pageNumber) {
        System.out.println(pageNumber);
        List<PollOverview> polls = pollService.getAllPollOverviews(pageNumber);
        System.out.println("All polls overviews gotten for page " + pageNumber + "!");
        return ResponseEntity.ok(polls);
    }

    @PostMapping
    public ResponseEntity<Boolean> createPoll(@RequestBody Poll newPoll) {
        boolean isSuccess = pollService.savePoll(newPoll);

        if (isSuccess) {
            return ResponseEntity.ok(Boolean.TRUE);
        } else {
            // If there is an error on the DB side for submitting the user's vote, then return this.
            System.out.println("Error, could not add poll");
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
