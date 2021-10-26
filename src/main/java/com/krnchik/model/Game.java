package com.krnchik.model;

public class Game {

    public static final int MAX_COUNT_ATTEMPT = 10;

    private final Word word;
    private int currentAttempt;

    public Game() {
        this.word = new WordStorage();
        this.currentAttempt = 1;
    }

    public boolean isWin() {
        return word.getHiddenWord().equals(word.getSearchWord());
    }

    public boolean isFail() {
        return currentAttempt > MAX_COUNT_ATTEMPT;
    }

    public void incrementAttempt() {
        currentAttempt++;
    }

    public boolean isGuessed(String old, String current) {
        return !old.equals(current);
    }

    public String getHiddenWord() {
        return word.getHiddenWord().replaceAll("", " ").trim();
    }

    public String tryGuess(char letter) {
        String hiddenWord = word.getHiddenWord();
        String currentHiddenWord = word.tryGuess(letter);
        if (hiddenWord.equals(currentHiddenWord))
            this.currentAttempt++;
        return currentHiddenWord.replaceAll("", " ").trim();
    }

    public void setWord(String word) {
        this.word.setSearchWord(word);
    }

    public Word getWord() {
        return word;
    }

    public int getCurrentAttempt() {
        return currentAttempt;
    }
}
