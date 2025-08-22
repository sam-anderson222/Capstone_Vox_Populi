package com.example.VoxPopuli.model;

public class PollVote {
    private Integer userId;
    private Integer pollId;
    private Integer optionNumber;

    public PollVote(){}

    public PollVote(Integer userId, Integer pollId, Integer optionNumber) {
        this.userId = userId;
        this.pollId = pollId;
        this.optionNumber = optionNumber;
    }

    // Getters
    public Integer getUserId() {
        return userId;
    }

    public Integer getPollId() {
        return pollId;
    }

    public Integer getOptionNumber() {
        return optionNumber;
    }


    // Setters
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setPollId(Integer pollId) {
        this.pollId = pollId;
    }

    public void setOptionNumber(Integer optionNumber) {
        this.optionNumber = optionNumber;
    }

    @Override
    public String toString() {
        return String.format("PollVote{userId=%d, pollId=%d, optionNumber=%d}", userId, pollId, optionNumber);
    }
}
