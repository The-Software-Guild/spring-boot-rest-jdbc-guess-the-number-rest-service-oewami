package com.oewami.guessTheNumber.dao;

import com.oewami.guessTheNumber.model.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
@Profile("prod")
public class RoundDatabaseDao implements RoundDao {

    private final JdbcTemplate template;

    @Autowired
    public RoundDatabaseDao(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public Round add(Round round) {
        final String sql = "INSERT INTO Round(gameId, guessTime, guess, result)" +
                " VALUES(?,?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        template.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, round.getGameId());
            statement.setTimestamp(2, round.getGuessTime());
            statement.setString(3, round.getGuess());
            statement.setString(4, round.getResult());
            return statement;

        }, keyHolder);

        round.setRoundId(keyHolder.getKey().intValue());

        return round;
    }

    @Override
    public List<Round> getRounds() {
        return null;
    }

    @Override
    public List<Round> getGameInfo(int game_id) {
        return null;
    }

    @Override
    public Round findRoundById(int round_id) {
        return null;
    }

    @Override
    public boolean update(Round round) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public List<Round> getRound(int roundId) {
        return null;
    }

    @Override
    public List<Round> getRoundsByGameId(int gameId) {
        final String SELECT_ROUNDS_GAMEID = "SELECT r.* FROM round r INNER JOIN game g ON r.gameId = g.gameId WHERE r.gameId = ?";
        try {
            List<Round> roundsByGameId = template.query(SELECT_ROUNDS_GAMEID, new RoundMapper(), gameId);
            return roundsByGameId;
        } catch (DataAccessException e) {
            return null;
        }
    }

    private static final class RoundMapper implements RowMapper<Round> {

        @Override
        public Round mapRow(ResultSet resultSet, int i) throws SQLException {
            Round round = new Round();
            round.setRoundId(resultSet.getInt("roundId"));
            round.setGame_id(resultSet.getInt("gameId"));
            round.setGuess(resultSet.getString("guess"));
            round.setGuessTime(resultSet.getTimestamp("guessDuration"));
            round.setResult(resultSet.getString("result"));
            return round;
        }
    }
}
