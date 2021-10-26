package com.krnchik.model;

public interface Word {
    String getHiddenWord();
    String tryGuess(char letter);
    void setSearchWord(String searchWord);
    String getSearchWord();
}
