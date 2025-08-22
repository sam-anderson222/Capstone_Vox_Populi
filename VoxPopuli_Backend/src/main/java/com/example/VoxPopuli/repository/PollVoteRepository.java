package com.example.VoxPopuli.repository;

import com.example.VoxPopuli.model.PollOptionVoteResult;
import com.example.VoxPopuli.model.PollVote;
import com.example.VoxPopuli.model.PollVoteWithOption;
import com.example.VoxPopuli.model.User;

import java.util.List;
import java.util.Optional;

public interface PollVoteRepository {
    boolean voteOnPoll(PollVote vote);
    Optional<PollVoteWithOption> userVotedOnPoll(User u, Integer pollId);
    List<PollOptionVoteResult> getResultsForPoll(Integer pollId);
}
