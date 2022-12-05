package com.oewami.guessTheNumber.model;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Game {

    private int gameId;
    private String answer;
    private boolean status;
    private Random random = new Random();

    public Game() {
        Set<Integer> numbers = new HashSet<>();
        while(numbers.size() < 4) {
            random.nextInt(10);
        }
        StringBuilder str = new StringBuilder();
        for(Integer num : numbers) {
            str.append(num);
        }
        this.answer = str.toString();
        this.status = false;
    }


    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    // every 4 digit number has a different digit
    //An exact match occurs when the user guesses the correct digit in the correct position.
    //A partial match occurs when the user guesses the correct digit but in the wrong position.
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String toString() {
        return String.format("ID: %s\nAnswer: %s\nStatus: %s", gameId, answer, status);
    }
}
