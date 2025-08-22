package com.example.VoxPopuli.repository.impl;

import com.example.VoxPopuli.model.*;
import com.example.VoxPopuli.repository.PollVoteRepository;
import com.example.VoxPopuli.repository.exceptions.DatabaseErrorException;
import com.example.VoxPopuli.repository.mappers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class mySqlPollVoteRepo implements PollVoteRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public boolean voteOnPoll(PollVote vote) {
        String sql = "INSERT INTO poll_votes(user_id, poll_id, option_number) VALUES (?, ?, ?)";

        try {
            jdbcTemplate.update(con -> {
                PreparedStatement ps = con.prepareStatement(sql);

                ps.setInt(1, vote.getUserId());
                ps.setInt(2, vote.getPollId());
                ps.setInt(3, vote.getOptionNumber());

                return ps;
            });

            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public Optional<PollVoteWithOption> userVotedOnPoll(User u, Integer pollId) {
        String sql = "{CALL get_vote_with_option(?, ?)}";

        try {
           PollVoteWithOption pv = jdbcTemplate.queryForObject(sql, PollVoteWithOptionMapper.pollVoteWithOptionRowMapper(), pollId, u.getUserId());
           return Optional.of(pv);
        } catch (EmptyResultDataAccessException ex) { // If no user found with this id.
            return Optional.empty();
        } catch (Exception ex) {
            throw new DatabaseErrorException();
        }
    }

    @Override
    public List<PollOptionVoteResult> getResultsForPoll(Integer pollId) {
        try {
            return jdbcTemplate.execute("{CALL get_results_for_poll(?)}",
                    (CallableStatementCallback<List<PollOptionVoteResult>>) cs -> {
                        cs.setInt(1, pollId);

                        boolean hasResults = cs.execute();
                        List<PollOptionVoteResult> pollOptionVoteResults = new ArrayList<>();

                        // Get all polls
                        if (hasResults) {
                            try (ResultSet rs = cs.getResultSet()) {
                                while (rs.next()) {
                                    pollOptionVoteResults.add(PollOptionVoteResultMapper.pollOptionVoteResultRowMapper().mapRow(rs, 1));
                                }
                            }
                        }

                        return pollOptionVoteResults;

                    });
        } catch (Exception ex) {
            throw new DatabaseErrorException();
        }
    }
}
