package com.sas.wordgame.controller;

import com.sas.wordgame.service.WordGameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@SpringBootTest
@AutoConfigureMockMvc
public class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WordGameService wordGameService;

    @BeforeEach
    void setUp() {
        wordGameService.init();
    }

    @Test
    public void testMakeGuessCorrect() throws Exception {
        String targetWord = wordGameService.getTargetWord();

        mockMvc.perform(post("/api/game/guess")
                        .param("guess", targetWord))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("GGGGG"))
                .andExpect(jsonPath("$.message").value("Congratulations! You've guessed the word. Starting a new game!"))
                .andExpect(jsonPath("$.attempts").value(0));
    }
    // @Test
    // public void testMaxAttemptsExceeded() throws Exception {
    //     // Simulate the maximum number of allowed guesses
    //     for (int i = 0; i < 5; i++) {
    //         mockMvc.perform(post("/api/game/guess")
    //                         .param("guess", "apple"))
    //                 .andExpect(status().isOk());
    //     }

    //     // The 6th attempt should result in the max attempts exceeded message
    //     mockMvc.perform(post("/api/game/guess")
    //                     .param("guess", "apple"))
    //             .andExpect(status().isOk())
    //             .andExpect(jsonPath("$.result").doesNotExist()) // Expect null result, so use doesNotExist()
    //             .andExpect(jsonPath("$.message").value("You have reached the maximum number of attempts. You failed! Want to try the game again?"))
    //             .andExpect(jsonPath("$.attempts").value(5)); // Verify that attempts are correctly reported as 6
    // }

    @Test
    public void testMakeGuessIncorrectPosition() throws Exception {
        String targetWord = wordGameService.getTargetWord();
        String guess = targetWord.substring(1) + targetWord.charAt(0);

        mockMvc.perform(post("/api/game/guess")
                        .param("guess", guess))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(containsString("Y")))
                .andExpect(jsonPath("$.attempts").value(1));
    }

    @Test
    public void testMakeGuessNotInWord() throws Exception {
        mockMvc.perform(post("/api/game/guess")
                        .param("guess", "zzzzz"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("BBBBB"))
                .andExpect(jsonPath("$.attempts").value(1));
    }


    @Test
    public void testMakeGuessWithInvalidLength() throws Exception {
        // Guess with fewer than 5 letters
        mockMvc.perform(post("/api/game/guess")
                        .param("guess", "abc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Guess must be exactly 5 letters long!"));

        // Guess with more than 5 letters
        mockMvc.perform(post("/api/game/guess")
                        .param("guess", "abcdef"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Guess must be exactly 5 letters long!"));
    }

    @Test
    public void testGetTargetWord() throws Exception {
        // Test to check if the target word is correctly retrieved (for debugging purposes only)
        mockMvc.perform(get("/api/game/target-word"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(wordGameService.getTargetWord()));
    }
}
