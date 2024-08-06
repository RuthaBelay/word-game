package com.sas.wordgame.controller;

import com.sas.wordgame.model.GuessResult;
import com.sas.wordgame.service.WordGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/game")
public class GameController {

    private final WordGameService wordGameService;

    @Autowired
    public GameController(WordGameService wordGameService) {
        this.wordGameService = wordGameService;
    }

    @PostMapping("/guess")
    public GuessResult makeGuess(@RequestParam("guess") String guess) {
        if (guess.length() != 5) {
            return new GuessResult(null, "Guess must be exactly 5 letters long!", null);
        }
        return wordGameService.makeGuess(guess.toLowerCase());
    }

    @GetMapping("/target-word")
    public String getTargetWord() {
        // For debugging purposes, you can expose the target word.
        return wordGameService.getTargetWord();
    }
}
