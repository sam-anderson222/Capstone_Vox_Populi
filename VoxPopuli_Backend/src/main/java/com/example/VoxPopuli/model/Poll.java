package com.example.VoxPopuli.model;

import java.util.List;

public class Poll {
    private Integer pollId;
    private Integer pollAuthor;
    private String pollTitle;
    private String pollDescription;
    private List<PollOption> options;

    public Poll() {}

    public Poll (Integer pollId, Integer pollAuthor, String pollTitle, String pollDescription, List<PollOption> options) {
        this.pollId = pollId;
        this.pollAuthor = pollAuthor;
        this.pollTitle = pollTitle;
        this.pollDescription = pollDescription;
        this.options = options;
    }

    // Getters

    public Integer getPollId() {
        return pollId;
    }

    public Integer getPollAuthor() {
        return pollAuthor;
    }

    public String getPollDescription() {
        return pollDescription;
    }

    public String getPollTitle() {
        return pollTitle;
    }

    public List<PollOption> getOptions() {
        return options;
    }

    // Setters


    public void setPollId(Integer pollId) {
        this.pollId = pollId;
    }

    public void setPollAuthor(Integer pollAuthor) {
        this.pollAuthor = pollAuthor;
    }

    public void setPollTitle(String pollTitle) {
        this.pollTitle = pollTitle;
    }

    public void setPollDescription(String pollDescription) {
        this.pollDescription = pollDescription;
    }

    public void setOptions(List<PollOption> options) {
        this.options = options;
    }

    public void addOption(PollOption po) {
        options.add(po);
    }

    @Override
    public String toString() {
        return String.format("Poll{pollID=%d, pollAuthor=%d, pollTitle=%s, pollDescription=%s, options=%s}", pollId, pollAuthor, pollTitle, pollDescription, options);
    }
}
