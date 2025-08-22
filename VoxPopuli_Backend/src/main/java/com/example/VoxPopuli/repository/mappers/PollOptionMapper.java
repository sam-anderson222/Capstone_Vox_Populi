package com.example.VoxPopuli.repository.mappers;

import com.example.VoxPopuli.model.PollOption;
import org.springframework.jdbc.core.RowMapper;

public class PollOptionMapper {
    public static RowMapper<PollOption> pollOptionRowMapper() {
        return (rs, rowNum) -> {
            PollOption po = new PollOption();

            po.setPollId(rs.getInt("poll_id"));
            po.setOptionNumber(rs.getInt("option_number"));
            po.setOptionName(rs.getString("option_name"));

            return po;
        };
    }
}
