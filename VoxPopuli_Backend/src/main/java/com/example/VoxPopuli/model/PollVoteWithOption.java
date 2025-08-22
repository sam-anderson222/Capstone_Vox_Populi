package com.example.VoxPopuli.model;

public class PollVoteWithOption {
    private Integer userId;
    private Integer pollId;
    private Integer optionNumber;
    private String optionName;

    public PollVoteWithOption(){}

    public PollVoteWithOption(Integer userId, Integer pollId, Integer optionNumber, String optionName) {
        this.userId = userId;
        this.pollId = pollId;
        this.optionNumber = optionNumber;
        this.optionName = optionName;
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

    public String getOptionName() {
        return optionName;
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

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    @Override
    public String toString() {
        return String.format("PollVoteWithOption{userId=%d, pollId=%d, optionNumber=%d, optionName=%s}", userId, pollId, optionNumber, optionName);
    }
}
