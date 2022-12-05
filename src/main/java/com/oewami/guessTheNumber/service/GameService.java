package com.oewami.guessTheNumber.service;

import com.oewami.guessTheNumber.model.Game;

import java.util.List;

public interface GameService {

    List<Game> listGames();
    Game getGameById(int gameId);

    Game addGame(Game game);

}
