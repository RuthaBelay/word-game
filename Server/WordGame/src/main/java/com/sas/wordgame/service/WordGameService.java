package com.sas.wordgame.service;

import com.sas.wordgame.model.GuessResult;
import com.sas.wordgame.model.WordList;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class WordGameService {

    private String targetWord;
    private int attempts;
    private final int maxAttempts = 6;
    private WordList wordList;

    @PostConstruct
    public void init() {
        wordList = new WordList(Arrays.asList(
                "apple", "grape", "lemon", "mango", "melon",
                "peach", "plumb", "pearl", "train", "vivid"
        ));
        resetGame();
    }

    public GuessResult makeGuess(String guess) {
        if (attempts >= maxAttempts) {
            String message = "You have reached the maximum number of attempts. You failed! Want to try the game again?";
            resetGame();
            return new GuessResult(null, message, attempts);
        }

        attempts++;

        char[] result = new char[5];
        Map<Character, Integer> targetCharCount = new HashMap<>();

        for (char c : targetWord.toCharArray()) {
            targetCharCount.put(c, targetCharCount.getOrDefault(c, 0) + 1);
        }

        for (int i = 0; i < guess.length(); i++) {
            char c = guess.charAt(i);
            if (c == targetWord.charAt(i)) {
                result[i] = 'G'; // G for Green, correct position
                targetCharCount.put(c, targetCharCount.get(c) - 1);
            } else if (targetCharCount.containsKey(c) && targetCharCount.get(c) > 0) {
                result[i] = 'Y'; // Y for Yellow, incorrect position
                targetCharCount.put(c, targetCharCount.get(c) - 1);
            } else {
                result[i] = 'B'; // B for Black, not in word
            }
        }

        boolean isCorrect = String.valueOf(result).equals("GGGGG");
        String message = isCorrect ? "Congratulations! You've guessed the word. Starting a new game!" : null;

        if (isCorrect || attempts >= maxAttempts) {
            if (!isCorrect) {
                message = "You have reached the maximum number of attempts. You failed! Want to try the game again?";
            }
            resetGame();
        }

        return new GuessResult(String.valueOf(result), message, attempts);
    }

    private void resetGame() {
        targetWord = wordList.getRandomWord();
        attempts = 0;
    }

    public String getTargetWord() {
        return targetWord;
    }
}
