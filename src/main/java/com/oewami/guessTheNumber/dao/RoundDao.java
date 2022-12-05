package com.oewami.guessTheNumber.dao;

import com.oewami.guessTheNumber.model.Round;

import java.util.List;

public interface RoundDao {

    Round add(Round round);
    List<Round> getRounds();
    List<Round> getGameInfo(int game_id);
    Round findRoundById(int round_id);
    boolean update(Round round);
    boolean deleteById(int id);

    List<Round> getRound(int roundId);

    List<Round> getRoundsByGameId(int gameId);
}
