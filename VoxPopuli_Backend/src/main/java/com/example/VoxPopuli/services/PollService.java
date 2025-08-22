package com.example.VoxPopuli.services;

import com.example.VoxPopuli.model.Poll;
import com.example.VoxPopuli.model.PollOverview;
import com.example.VoxPopuli.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class PollService {
    @Autowired
    private PollRepository pollRepo;

    public PollService(){}

    public List<Poll> getAllPolls() {
        return pollRepo.getAllPolls();
    }

    public Optional<Poll> getPollById(Integer pollId) {
        if (pollId < 1) {
            return Optional.empty();
        }

        return pollRepo.getPollById(pollId);
    }

    public List<PollOverview> getAllPollOverviews() {
        return pollRepo.getAllPollOverviews();
    }

    public boolean savePoll(Poll poll) {
        if (poll.getOptions().size() < 2) {
            return false;
        }

        return pollRepo.savePoll(poll);
    }

    public boolean deletePollById(Integer pollId) {
        return pollRepo.deletePollById(pollId);
    }
}
