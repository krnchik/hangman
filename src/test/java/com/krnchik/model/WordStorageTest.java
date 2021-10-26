package com.krnchik.model;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class WordStorageTest {

    WordStorage wordStorage;

    @Before
    public void setUp() {
        wordStorage = new WordStorage("Hair");
    }

    @Test
    public void setWord() {
        wordStorage.setSearchWord("Offer");
        assertThat(wordStorage.getSearchWord()).isEqualTo("OFFER");
        assertThat(wordStorage.getHiddenWord()).isEqualTo("_____");
        Map<Character, Integer> countLetters = new HashMap<>();
        countLetters.put('O', 1);
        countLetters.put('F', 2);
        countLetters.put('E', 1);
        countLetters.put('R', 1);
        assertThat(wordStorage.getCountLetters()).isEqualTo(countLetters);

        wordStorage.setSearchWord("thriller ");
        assertThat(wordStorage.getSearchWord()).isEqualTo("THRILLER");
        assertThat(wordStorage.getHiddenWord()).isEqualTo("________");
        countLetters = new HashMap<>();
        countLetters.put('T', 1);
        countLetters.put('H', 1);
        countLetters.put('R', 2);
        countLetters.put('L', 2);
        countLetters.put('E', 1);
        countLetters.put('I', 1);
        assertThat(wordStorage.getCountLetters()).isEqualTo(countLetters);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setWrongWord() {
        wordStorage.setSearchWord("");
        wordStorage.setSearchWord("  ");
        wordStorage.setSearchWord("dddddddddddddddddddddddddddddddddddddddddd");
        wordStorage.setSearchWord("Hello world");
        wordStorage.setSearchWord(null);
    }

    @Test
    public void tryGuess() {
        assertThat(wordStorage.tryGuess('Z')).isEqualTo("____");
        assertThat(wordStorage.tryGuess('A')).isEqualTo("_A__");
        assertThat(wordStorage.tryGuess('H')).isEqualTo("HA__");
        assertThat(wordStorage.tryGuess('P')).isEqualTo("HA__");
        assertThat(wordStorage.tryGuess('R')).isEqualTo("HA_R");
    }

    @Test
    public void giveInitialHiddenWord() {
        assertThat(wordStorage.giveInitialHiddenWord()).isEqualTo("____");
    }
}