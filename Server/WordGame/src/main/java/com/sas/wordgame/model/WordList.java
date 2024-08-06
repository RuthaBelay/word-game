package com.sas.wordgame.model;


import java.util.List;
import java.util.Random;

public class WordList {

    private List<String> words;

    public WordList(List<String> words) {
        this.words = words;
    }

    public String getRandomWord() {
        Random random = new Random();
        return words.get(random.nextInt(words.size()));
    }

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }
}

