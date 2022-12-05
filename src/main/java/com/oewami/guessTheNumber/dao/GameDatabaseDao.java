package com.oewami.guessTheNumber.dao;

import com.oewami.guessTheNumber.model.Game;
import com.oewami.guessTheNumber.model.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;


import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

@Repository
@Profile("prod")
public class GameDatabaseDao implements GameDao {

    private Map<Integer, Game> games = new HashMap<>();

    @Autowired
    JdbcTemplate jdbcTemplate;

    //     * "game/{gameId}" - GET – Returns a specific
    //     game based on ID. Be sure in-progress games
    //     do not display their answer.
    @Override
    public Game getGameById(int gameId) {
        final String SELECT_GAME = "SELECT gameId, status, CASE WHEN g.status = false THEN g.answer=\"0\" ELSE g.answer END AS answer FROM game g WHERE gameId = ?";
        try {
            return jdbcTemplate.queryForObject(SELECT_GAME, new GameMapper(), gameId);
        } catch (DataAccessException e) {
            return null;
        }
    }

    //     * "begin" - POST – Starts a game, generates an answer,
    //     and sets the correct status. Should return a 201 CREATED
    //     message as well as the created gameId.
    @Override
    public Game addGame(Game game) {
        game.setStatus(false);
        final String INSERT_GUESS = "INSERT INTO game (answer, status) VALUES (?,?)";
        try {
            jdbcTemplate.update((Connection conn) -> {
                PreparedStatement statement = conn.prepareStatement(
                        INSERT_GUESS, Statement.RETURN_GENERATED_KEYS
                );
                statement.setString(1, game.getAnswer());
                return statement;
            });
        } catch (DataAccessException e) {
            System.out.println("cannot add game");
        }
        return game;
    }


    //     * "game" – GET – Returns a list of all games.
    //     Be sure in-progress games do not display their answer.
    @Override
    public List<Game> listGames() {
        final String SELECT_GAMES = "SELECT gameId, status, CASE WHEN g.status = false THEN g.answer=\"****\" ELSE g.answer END AS answer FROM game g;";
        return jdbcTemplate.query(SELECT_GAMES, new GameMapper());
    }


    //      * "rounds/{gameId} – GET – Returns a list of
    //      rounds for the specified game sorted by time.
//    public List<Round> getRound(int roundId) {
//        final String SELECT_ROUNDS_BY_GAMEID = "SELECT * from round WHERE gameId = ?";
//        return jdbcTemplate.query(SELECT_ROUNDS_BY_GAMEID, new RoundMapper());
//    }

    private static class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet resultSet, int i) throws SQLException {
            Game game = new Game();
            game.setGameId(resultSet.getInt("gameId"));
            game.setAnswer(resultSet.getString("answer"));
            game.setStatus(resultSet.getBoolean("status"));
            return game;
        }
    }


}
