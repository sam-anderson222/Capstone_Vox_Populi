package com.example.VoxPopuli.model;

public class PollOptionVoteResult {
    private Integer optionNumber;
    private String optionName;
    private Integer optionVotes;

    public PollOptionVoteResult (){}

    public PollOptionVoteResult(Integer optionNumber, String optionName, Integer optionVotes) {
        this.optionNumber = optionNumber;
        this.optionName = optionName;
        this.optionVotes = optionVotes;
    }

    // Getters
    public Integer getOptionNumber() {
        return optionNumber;
    }

    public String getOptionName() {
        return optionName;
    }

    public Integer getOptionVotes() {
        return optionVotes;
    }

    // Setters
    public void setOptionNumber(Integer optionNumber) {
        this.optionNumber = optionNumber;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public void setOptionVotes(Integer optionVotes) {
        this.optionVotes = optionVotes;
    }

    @Override
    public String toString() {
        return String.format("PollOptionVoteResult{optionNumber=%d, optionName=%s, optionVotes=%d}", optionNumber, optionName, optionVotes);
    }
}
