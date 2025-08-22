package com.example.VoxPopuli.repository.impl;

import com.example.VoxPopuli.model.PollOption;
import com.example.VoxPopuli.repository.PollOptionRepository;
import com.example.VoxPopuli.repository.exceptions.DatabaseErrorException;
import com.example.VoxPopuli.repository.mappers.PollOptionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@Repository
@Primary
public class mySqlPollOptionRepo implements PollOptionRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<PollOption> getAllPollOptions() {
        String sql = "SELECT * FROM poll_options";

        try {
            return jdbcTemplate.query(sql, PollOptionMapper.pollOptionRowMapper());
        } catch (Exception ex) {
            throw new DatabaseErrorException();
        }
    }

    @Override
    public List<PollOption> getAllPollOptionsForPoll(Integer pollId) {
        String sql = "SELECT * FROM poll_options WHERE poll_id = ? ORDER BY option_number";

        try {
            List<PollOption> options = jdbcTemplate.query(sql, PollOptionMapper.pollOptionRowMapper(), pollId);
            if (options.size() < 2) {
                throw new DatabaseErrorException();
            } else {
                return options;
            }
        } catch (Exception ex) {
            throw new DatabaseErrorException();
        }
    }

    @Override
    public boolean saveOptionsForPoll(Integer pollId, List<PollOption> options) {
        String sql = "INSERT INTO poll_options (poll_id, option_number, option_name) VALUES (?, ?, ?)";

        try {
            jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    PollOption po = options.get(i);
                    ps.setInt(1, pollId);
                    ps.setInt(2, po.getOptionNumber());
                    ps.setString(3, po.getOptionName());
                }

                @Override
                public int getBatchSize() {
                    return options.size();
                }
            });

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
