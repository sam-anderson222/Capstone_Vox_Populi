package com.example.VoxPopuli.model;

public class PollOption {
    private Integer pollId;
    private Integer optionNumber;
    private String optionName;

    // Default Constructor
    public PollOption() {}

    // Constructor with parameters
    public PollOption(Integer pollId, Integer optionNumber, String optionName) {
        this.pollId = pollId;
        this.optionNumber = optionNumber;
        this.optionName = optionName;

    }

    // Getters
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
        return String.format("PollOption{pollId=%d, optionNumber=%d, optionName=%s}", pollId, optionNumber, optionName);
    }
}
