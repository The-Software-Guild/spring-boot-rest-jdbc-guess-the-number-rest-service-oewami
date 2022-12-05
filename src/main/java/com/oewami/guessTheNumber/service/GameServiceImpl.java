package com.oewami.guessTheNumber.service;

import com.oewami.guessTheNumber.dao.GameDao;
import com.oewami.guessTheNumber.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameDao gameDao;

    public GameServiceImpl(GameDao gameDao) {this.gameDao = gameDao;}

    @Override
    public List<Game> listGames() {
        List<Game> games = gameDao.listGames();
        for(Game game : games) {
            if(!game.getStatus()) {
                game.setAnswer("****");
            }
        }
        return games;
    }

    @Override
    public Game getGameById(int gameId) {
        Game game = gameDao.getGameById(gameId);
        if(!game.getStatus()) {
            game.setAnswer("****");
        }
        return game;
    }

    @Override
    public Game addGame(Game game) {
        return gameDao.addGame(game);
    }

}
