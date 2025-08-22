package com.example.VoxPopuli.repository;

import com.example.VoxPopuli.model.PollOption;

import java.util.List;
import java.util.Optional;

public interface PollOptionRepository {
    List<PollOption>getAllPollOptions();
    List<PollOption> getAllPollOptionsForPoll(Integer pollId);
    boolean saveOptionsForPoll(Integer pollId, List<PollOption> options);
}
