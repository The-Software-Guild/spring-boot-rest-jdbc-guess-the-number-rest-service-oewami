package com.oewami.guessTheNumber.model;

import java.sql.Timestamp;

public class Round {

    private int roundId;
    private int gameId;
    private String guess;
    private Timestamp guessTime;
    private String result;

    public int getRoundId() {
        return roundId;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGame_id(int gameId) {
        this.gameId = gameId;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public Timestamp getGuessTime() {
        return guessTime;
    }

    public void setGuessTime(Timestamp guessTime) {
        this.guessTime = guessTime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
