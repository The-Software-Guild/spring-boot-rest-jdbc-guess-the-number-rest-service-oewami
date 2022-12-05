package com.oewami.guessTheNumber.dao;

import com.oewami.guessTheNumber.model.Game;

import java.util.List;

public interface GameDao {

    List<Game> listGames();
    Game getGameById(int gameId);

    Game addGame(Game game);
}
