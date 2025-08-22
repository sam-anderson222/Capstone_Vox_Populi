package com.example.VoxPopuli.repository.mappers;

import com.example.VoxPopuli.model.PollVote;
import com.example.VoxPopuli.model.PollVoteWithOption;
import org.springframework.jdbc.core.RowMapper;

public class PollVoteWithOptionMapper {
    public static RowMapper<PollVoteWithOption> pollVoteWithOptionRowMapper() {
        return (rs, rowNum) -> {
            PollVoteWithOption pv = new PollVoteWithOption();

            pv.setUserId(rs.getInt("user_id"));
            pv.setPollId(rs.getInt("poll_id"));
            pv.setOptionNumber(rs.getInt("option_number"));
            pv.setOptionName(rs.getString("option_name"));

            return pv;
        };
    }
}
