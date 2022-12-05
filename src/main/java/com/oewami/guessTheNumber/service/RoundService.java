package com.oewami.guessTheNumber.service;

import com.oewami.guessTheNumber.model.Game;
import com.oewami.guessTheNumber.model.Round;

import java.util.List;

public interface RoundService {
    List<Round> getRoundsByGameId(int gameId);

    Round guess(Game game, Game answer);
}
