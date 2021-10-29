package com.krnchik.controller;

import com.krnchik.model.Game;

public class AlphabetController implements Controller {

    private final Game game;

    public AlphabetController(Game game) {
        this.game = game;
    }

    @Override
    public boolean action(Character letter) {
        String oldWord = game.getHiddenWord();
        String newWord = game.tryGuess(letter);
        return game.isGuessed(oldWord, newWord);
    }
}
