package com.example.VoxPopuli.services;


import com.example.VoxPopuli.model.PollOption;
import com.example.VoxPopuli.repository.PollOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class PollOptionService {
    @Autowired
    private PollOptionRepository pollOptionRepository;

    public PollOptionService(){}


    public List<PollOption> getAllPollOptions() {
        return pollOptionRepository.getAllPollOptions();
    }

    public List<PollOption> getAllPollOptionsForPoll(Integer pollId) {
        return pollOptionRepository.getAllPollOptionsForPoll(pollId);
    }

    public boolean saveOptionsForPoll(Integer pollId, List<PollOption> options) {
        if (options.size() < 2 || pollId < 1) {
            return false;
        }

        return pollOptionRepository.saveOptionsForPoll(pollId, options);
    }
}
