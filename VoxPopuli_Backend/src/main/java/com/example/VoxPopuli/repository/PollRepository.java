package com.example.VoxPopuli.repository;

import com.example.VoxPopuli.model.Poll;
import com.example.VoxPopuli.model.PollOverview;
import com.example.VoxPopuli.model.PollVote;

import java.util.List;
import java.util.Optional;

public interface PollRepository {
    List<Poll> getAllPolls();
    Optional<Poll> getPollById(Integer pollId);
    List<PollOverview> getAllPollOverviews();
    boolean savePoll(Poll poll);
    boolean deletePollById(Integer pollId);
}
