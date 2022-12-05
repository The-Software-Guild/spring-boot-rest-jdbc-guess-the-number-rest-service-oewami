package com.oewami.guessTheNumber.controller;

import com.oewami.guessTheNumber.model.Game;
import com.oewami.guessTheNumber.model.Round;
import com.oewami.guessTheNumber.service.GameService;
import com.oewami.guessTheNumber.service.GameServiceImpl;
import com.oewami.guessTheNumber.service.RoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/guessNumber")
public class GameController {

    private final GameService gameService;
    private final RoundService roundService;

    @Autowired
    public GameController(GameService gameService, RoundService roundService) {
        this.gameService = gameService;
        this.roundService = roundService;
    }

    //     * "begin" - POST – Starts a game, generates an answer,
    //     and sets the correct status. Should return a 201 CREATED
    //     message as well as the created gameId.
    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public Game startGame(@RequestBody Game game) {
        return gameService.addGame(game);
    }


    //     * "guess" – POST – Makes a guess by passing the guess and gameId
    //     in as JSON. The program must calculate the results of the guess
    //     and mark the game finished if the guess is correct. It returns
    //     the Round object with the results filled in.
    @PostMapping("/guess")
    @ResponseStatus(HttpStatus.CREATED)
    public Round guess(@RequestBody Game game) {
        Game answer = gameService.getGameById(game.getGameId());
        return roundService.guess(game, answer);
    }


    //     * "game" – GET – Returns a list of all games.
    //     Be sure in-progress games do not display their answer.
    @GetMapping("/game")
    public List<Game> getGames() {
        return gameService.listGames();
    }


    //     * "game/{gameId}" - GET – Returns a specific
    //     game based on ID. Be sure in-progress games
    //     do not display their answer.
    @GetMapping("/game/{gameId}")
    public Game getGameById(@PathVariable int gameId) {
        return gameService.getGameById(gameId);
    }

//          * "rounds/{gameId} – GET – Returns a list of
//          rounds for the specified game sorted by time.
    @GetMapping("/rounds/{gameId}")
    public List<Round> getRound(@PathVariable int gameId) {
        return roundService.getRoundsByGameId(gameId);
    }

}
