package com.example.VoxPopuli.repository.mappers;

import com.example.VoxPopuli.model.PollOptionVoteResult;
import com.example.VoxPopuli.model.PollOverview;
import org.springframework.jdbc.core.RowMapper;

public class PollOptionVoteResultMapper {
    public static RowMapper<PollOptionVoteResult> pollOptionVoteResultRowMapper() {
        return (rs, rowNum) -> {
            PollOptionVoteResult po = new PollOptionVoteResult();

            po.setOptionNumber(rs.getInt("option_number"));
            po.setOptionName(rs.getString("option_name"));
            po.setOptionVotes(rs.getInt("number_votes"));

            return po;
        };
    }
}
