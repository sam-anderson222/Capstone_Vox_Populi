package com.example.VoxPopuli.repository.impl;

import com.example.VoxPopuli.model.*;
import com.example.VoxPopuli.repository.PollOptionRepository;
import com.example.VoxPopuli.repository.PollRepository;
import com.example.VoxPopuli.repository.exceptions.DatabaseErrorException;
import com.example.VoxPopuli.repository.mappers.PollMapper;
import com.example.VoxPopuli.repository.mappers.PollOptionMapper;
import com.example.VoxPopuli.repository.mappers.PollOverviewMapper;
import com.example.VoxPopuli.repository.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@Repository
@Primary
public class mySqlPollRepo implements PollRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PollOptionRepository pollOptionRepository;

    @Override
    public List<Poll> getAllPolls() {
        try {
            return jdbcTemplate.execute("{CALL get_all_polls_with_options()}",
                    (CallableStatementCallback<List<Poll>>) cs -> {
                        boolean hasResults = cs.execute();
                        HashMap<Integer, Poll> polls = new HashMap<>();

                        // Get all polls
                        if (hasResults) {
                            try (ResultSet rs = cs.getResultSet()) {
                                while (rs.next()) {
                                    Poll p = PollMapper.pollRowMapper().mapRow(rs, 1);

                                    polls.put(p.getPollId(), p);
                                }
                            }
                        }

                        hasResults = cs.getMoreResults();

                        // Getting all poll options for polls
                        if (hasResults) {
                            try (ResultSet rs = cs.getResultSet()) {
                                while (rs.next()) {
                                    PollOption po = PollOptionMapper.pollOptionRowMapper().mapRow(rs,1);

                                    polls.get(po.getPollId()).addOption(po);
                                }
                            }
                        }

                        return new ArrayList<>(polls.values());

                    });
        } catch (Exception ex) {
            throw new DatabaseErrorException();
        }
    }

    @Override
    public Optional<Poll> getPollById(Integer pollId) {
        String sql = "SELECT * FROM poll WHERE poll_id = ?";

        try {
            Poll poll = jdbcTemplate.queryForObject(sql, PollMapper.pollRowMapper(), pollId); // Get poll object
            poll.setOptions(pollOptionRepository.getAllPollOptionsForPoll(poll.getPollId())); // Get options for poll.
            return Optional.of(poll);
        } catch (EmptyResultDataAccessException ex) { // If no user found with this id.
            return Optional.empty();
        } catch (Exception ex) {
            throw new DatabaseErrorException();
        }
    }

    @Override
    public List<PollOverview> getAllPollOverviews() {
        try {
            return jdbcTemplate.execute("{CALL get_poll_overviews()}",
                    (CallableStatementCallback<List<PollOverview>>) cs -> {
                        boolean hasResults = cs.execute();

                        HashMap<Integer, PollOverview> overviews = new HashMap<>();

                        // Get poll overview info
                        if (hasResults) {
                            try (ResultSet rs = cs.getResultSet()) {
                                while (rs.next()) {
                                    PollOverview pollOverview = PollOverviewMapper.pollOverviewRowMapper().mapRow(rs, 1);

                                    overviews.put(pollOverview.getPollId(), pollOverview);
                                }
                            }
                        }

                        // Get total votes on each poll.
                        hasResults = cs.getMoreResults();

                        if (hasResults) {
                            try (ResultSet rs = cs.getResultSet()) {
                                while (rs.next()) {
                                    Integer pollId = rs.getInt("poll_id");
                                    Integer pollVotes = rs.getInt("votes");

                                    overviews.get(pollId).setVotesOnPoll(pollVotes);
                                }
                            }
                        }

                        return new ArrayList<>(overviews.values());

                    });
        } catch (Exception ex) {
            throw new DatabaseErrorException();
        }
    }

    @Override
    public boolean savePoll(Poll poll) {
        String sql = "INSERT INTO poll (poll_author, poll_title, poll_description) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        // Add the poll to the DB
        try {
            jdbcTemplate.update(con -> {
                PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

                ps.setInt(1, poll.getPollAuthor());
                ps.setString(2, poll.getPollTitle());
                ps.setString(3, poll.getPollDescription());

                return ps;
            }, keyHolder);

            poll.setPollId(keyHolder.getKey().intValue());
        } catch (Exception e) {
            deletePollById(poll.getPollId()); // If adding the poll fails, attempt to delete if from the DB.
            return false;
        }

        // Add the options to the poll now that we have the pollID.
        boolean addedOptionsSuccessfully = pollOptionRepository.saveOptionsForPoll(poll.getPollId(), poll.getOptions());

        if (addedOptionsSuccessfully) {
            return true;
        } else {
            deletePollById(poll.getPollId()); // If we fail to add the options to the poll, delete the poll from the DB.
            return false;
        }

    }

    // Calls a SPROC that deletes a poll, all its options, and its votes.
    @Override
    public boolean deletePollById(Integer pollId) {
        String sql = "{CALL delete_poll(?)}";

        try {
            Poll p = jdbcTemplate.execute("{CALL delete_poll(?)}",
                    (CallableStatementCallback<Poll>) cs -> {
                        cs.setInt(1, pollId);
                        cs.execute();

                        return null;
                    });

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
