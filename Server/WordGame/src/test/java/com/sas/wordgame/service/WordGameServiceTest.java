package com.sas.wordgame.service;


import com.sas.wordgame.model.GuessResult;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WordGameServiceTest {

    private WordGameService wordGameService;

    @BeforeEach
    void setUp() {
        wordGameService = new WordGameService();
        wordGameService.init();
    }

    @Test
    void testExactMatch() {
        GuessResult result = wordGameService.makeGuess(wordGameService.getTargetWord());
        assertThat(result.getResult()).isEqualTo("GGGGG");
        assertThat(result.getMessage()).isEqualTo("Congratulations! You've guessed the word. Starting a new game!");
        assertThat(result.getAttempts()).isEqualTo(0);
    }

    @Test
    void testPartialMatch() {
        String targetWord = wordGameService.getTargetWord();
        // Construct a guess with shuffled letters that should not form a perfect match
        String guess = targetWord.substring(1) + targetWord.charAt(0);

        GuessResult result = wordGameService.makeGuess(guess);
        assertThat(result.getResult()).containsPattern("[GYB]{5}");
        assertThat(result.getMessage()).isNull();
        assertThat(result.getAttempts()).isEqualTo(1);
    }

    @Test
    void testNoMatch() {
        GuessResult result = wordGameService.makeGuess("zzzzz");
        assertThat(result.getResult()).isEqualTo("BBBBB");
        assertThat(result.getMessage()).isNull();
        assertThat(result.getAttempts()).isEqualTo(1);
    }

    @Test
    void testExceedMaxAttempts() {
        for (int i = 0; i < 5; i++) {
            wordGameService.makeGuess("apple");
        }
        // Sixth attempt
        GuessResult result = wordGameService.makeGuess("apple");
        assertThat(result.getMessage()).isEqualTo("You have reached the maximum number of attempts. You failed! Want to try the game again?");
        assertThat(result.getResult()).isNotNull(); // The result should reflect the final attempt
    }
}