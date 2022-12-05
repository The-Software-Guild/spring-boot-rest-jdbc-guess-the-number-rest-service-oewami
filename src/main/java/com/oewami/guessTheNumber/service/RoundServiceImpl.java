package com.oewami.guessTheNumber.service;

import com.oewami.guessTheNumber.dao.RoundDao;
import com.oewami.guessTheNumber.model.Game;
import com.oewami.guessTheNumber.model.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoundServiceImpl implements RoundService {

    private RoundDao roundDao;

    @Autowired
    RoundServiceImpl(RoundDao roundDao) {this.roundDao = roundDao;}

    @Override
    public List<Round> getRoundsByGameId(int gameId) {
        return roundDao.getRoundsByGameId(gameId);
    }

    //     * "guess" – POST – Makes a guess by passing the guess and gameId
    //     in as JSON. The program must calculate the results of the guess
    //     and mark the game finished if the guess is correct. It returns
    //     the Round object with the results filled in.
    @Override
    public Round guess(Game game, Game answer) {
        Set<Character> answerSet = answer.getAnswer().chars().mapToObj(e ->(char)e).collect(Collectors.toSet());
        String guess = game.getAnswer();
        int len = answer.getAnswer().length();
        int e = 0;
        int p = 0;
        for(Character c : answerSet) {
            System.out.println(c);
        }
        for(int i = 0; i < len; i++) {
            if(answerSet.contains(guess.charAt(i))) {
                if(answer.getAnswer().charAt(i) == guess.charAt(i)) {
                    e++;
                } else {
                    p++;
                }
            }
        }
        String result = "e:" + e + ":p:" + p;
        Round round = new Round();
        round.setGame_id(answer.getGameId());
        round.setResult(result);
        round.setGuess(game.getAnswer());

        return round;

    }

}
