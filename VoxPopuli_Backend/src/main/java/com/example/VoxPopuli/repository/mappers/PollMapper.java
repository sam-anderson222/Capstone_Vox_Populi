package com.example.VoxPopuli.repository.mappers;

import com.example.VoxPopuli.model.Poll;
import org.springframework.jdbc.core.RowMapper;

import java.util.ArrayList;

public class PollMapper {
    public static RowMapper<Poll> pollRowMapper() {
        return (rs, rowNum) -> {
            Poll poll = new Poll();

            poll.setPollId(rs.getInt("poll_id"));
            poll.setPollAuthor(rs.getInt("poll_author"));
            poll.setPollTitle(rs.getString("poll_title"));
            poll.setPollDescription(rs.getString("poll_description"));
            poll.setOptions(new ArrayList<>()); // Initialized so can be added to later.

            return poll;
        };
    }
}
