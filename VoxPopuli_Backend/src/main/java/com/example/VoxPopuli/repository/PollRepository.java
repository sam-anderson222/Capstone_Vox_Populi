package com.example.VoxPopuli.repository;

import com.example.VoxPopuli.model.Poll;
import com.example.VoxPopuli.model.PollOverview;
import com.example.VoxPopuli.model.PollVote;

import java.util.List;
import java.util.Optional;

public interface PollRepository {
    List<Poll> getAllPolls();
    Integer getNumberOfPolls();
    Optional<Poll> getPollById(Integer pollId);
    List<PollOverview> getAllPollOverviews(Integer pageNumber);
    boolean savePoll(Poll poll);
    boolean deletePollById(Integer pollId);
}
