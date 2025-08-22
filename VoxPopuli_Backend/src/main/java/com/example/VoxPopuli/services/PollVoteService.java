package com.example.VoxPopuli.services;

import com.example.VoxPopuli.model.PollOptionVoteResult;
import com.example.VoxPopuli.model.PollVote;
import com.example.VoxPopuli.model.PollVoteWithOption;
import com.example.VoxPopuli.model.User;
import com.example.VoxPopuli.repository.PollVoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class PollVoteService {
    @Autowired
    private PollVoteRepository pollVoteRepo;

    public PollVoteService(){}

    public boolean voteOnPoll(PollVote vote) {
        return pollVoteRepo.voteOnPoll(vote);
    }


    public Optional<PollVoteWithOption> userVotedOnPoll(User u, Integer pollId) {
        return pollVoteRepo.userVotedOnPoll(u, pollId);
    }


    public List<PollOptionVoteResult> getResultsForPoll(Integer pollId) {
        return pollVoteRepo.getResultsForPoll(pollId);
    }
}
