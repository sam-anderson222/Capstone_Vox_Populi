package com.example.VoxPopuli.model;
// A poll overview contains extra info about a poll, like the author's username and how many votes are on a poll.
// Does not go to any database object, is purely for front-end display.

public class PollOverview {
    private Integer pollId;
    private Integer pollAuthorId;
    private String pollAuthorUsername;
    private String pollTitle;
    private String pollDescription;
    private Integer votesOnPoll;

    public PollOverview() {}

    public PollOverview (Integer pollId, Integer pollAuthorId, String pollAuthorUsername, String pollTitle, String pollDescription, Integer votesOnPoll) {
        this.pollId = pollId;
        this.pollAuthorId = pollAuthorId;
        this.pollAuthorUsername = pollAuthorUsername;
        this.pollTitle = pollTitle;
        this.pollDescription = pollDescription;
        this.votesOnPoll = votesOnPoll;
    }

    // Getters
    public Integer getPollId() {
        return pollId;
    }

    public Integer getPollAuthorId() {
        return pollAuthorId;
    }

    public String getPollAuthorUsername() {
        return pollAuthorUsername;
    }

    public String getPollDescription() {
        return pollDescription;
    }

    public String getPollTitle() {
        return pollTitle;
    }

    public Integer getVotesOnPoll() {
        return votesOnPoll;
    }

    // Setters
    public void setPollId(Integer pollId) {
        this.pollId = pollId;
    }

    public void setPollAuthorId(Integer pollAuthorId) {
        this.pollAuthorId = pollAuthorId;
    }

    public void setPollAuthorUsername(String pollAuthorUsername) {
        this.pollAuthorUsername = pollAuthorUsername;
    }

    public void setPollTitle(String pollTitle) {
        this.pollTitle = pollTitle;
    }

    public void setPollDescription(String pollDescription) {
        this.pollDescription = pollDescription;
    }

    public void setVotesOnPoll(Integer votesOnPoll) {
        this.votesOnPoll = votesOnPoll;
    }

    @Override
    public String toString() {
        return String.format("PollOverview{pollID=%d, pollAuthorId=%d, pollAuthorUsername=%s pollTitle=%s, pollDescription=%s, votesOnPoll=%d}",
                pollId, pollAuthorId, pollAuthorUsername, pollTitle, pollDescription, votesOnPoll);
    }
}
