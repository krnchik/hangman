package com.krnchik.model;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class WordStorage implements Word {

    public static final int MAX_WORD_LENGTH = 25;

    private String searchWord;
    private String hiddenWord;
    private Map<Character, Integer> countLetters;

    public WordStorage() {
    }

    public WordStorage(String searchWord) {
        setSearchWord(searchWord);
    }

    @Override
    public void setSearchWord(String searchWord) throws IllegalArgumentException {
        searchWord = searchWord.toUpperCase(Locale.ROOT).trim();
        if(!isWord(searchWord))
            throw new IllegalArgumentException(searchWord);
        this.searchWord = searchWord;
        this.hiddenWord = giveInitialHiddenWord();
        countingLetters(searchWord);
    }

    private void countingLetters(String word) {
        countLetters = new HashMap<>();
        for (int i = 0; i < word.length(); i++) {
            int count = 1;
            char letter = word.charAt(i);
            if (countLetters.containsKey(letter)) {
                count = countLetters.get(letter) + 1;
                countLetters.put(letter, count);
                continue;
            }
            countLetters.put(letter, count);
        }
    }

    @Override
    public String tryGuess(char letter) {
        if (searchWord == null)
            throw new IllegalArgumentException("Hasn't word");
        if (!countLetters.containsKey(letter))
            return hiddenWord;
        hiddenWord = giveHiddenWord(letter);
        return hiddenWord;
    }

    private String giveHiddenWord(char letter) {
        int first = 0;
        StringBuilder hiddenWordSB = new StringBuilder(hiddenWord);
        for (int i = 0; i < countLetters.get(letter); i++) {
            int guessedIndex = searchWord.indexOf(letter, first);
            hiddenWordSB.setCharAt(guessedIndex, letter);
            first = guessedIndex + 1;
        }
        return hiddenWordSB.toString();
    }

    public String giveInitialHiddenWord() {
        if (searchWord == null)
            throw new IllegalArgumentException("Hasn't word");
        return "_".repeat(searchWord.length()).trim();
    }

    private boolean isWord(String word) {
        return word != null && !word.isBlank()
                && word.matches("[A-Z]+") && word.length() < MAX_WORD_LENGTH;
    }

    public Map<Character, Integer> getCountLetters() {
        return countLetters;
    }

    public String getSearchWord() {
        return searchWord;
    }

    @Override
    public String getHiddenWord() {
        return hiddenWord;
    }
}
