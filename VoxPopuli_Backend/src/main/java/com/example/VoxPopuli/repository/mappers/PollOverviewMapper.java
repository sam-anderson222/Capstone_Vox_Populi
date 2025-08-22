package com.example.VoxPopuli.repository.mappers;

import com.example.VoxPopuli.model.Poll;
import com.example.VoxPopuli.model.PollOverview;
import org.springframework.jdbc.core.RowMapper;

public class PollOverviewMapper {
    public static RowMapper<PollOverview> pollOverviewRowMapper() {
        return (rs, rowNum) -> {
            PollOverview poll = new PollOverview();

            poll.setPollId(rs.getInt("poll_id"));
            poll.setPollAuthorId(rs.getInt("poll_author"));
            poll.setPollAuthorUsername(rs.getString("username"));
            poll.setPollTitle(rs.getString("poll_title"));
            poll.setPollDescription(rs.getString("poll_description"));

            return poll;
        };
    }
}
