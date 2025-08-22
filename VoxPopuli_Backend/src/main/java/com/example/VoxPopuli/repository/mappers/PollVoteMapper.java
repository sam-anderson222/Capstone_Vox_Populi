package com.example.VoxPopuli.repository.mappers;

import com.example.VoxPopuli.model.PollOption;
import com.example.VoxPopuli.model.PollVote;
import org.springframework.jdbc.core.RowMapper;

public class PollVoteMapper {
    public static RowMapper<PollVote> pollVoteRowMapper() {
        return (rs, rowNum) -> {
            PollVote pv = new PollVote();

            pv.setUserId(rs.getInt("user_id"));
            pv.setPollId(rs.getInt("poll_id"));
            pv.setOptionNumber(rs.getInt("option_number"));

            return pv;
        };
    }
}
