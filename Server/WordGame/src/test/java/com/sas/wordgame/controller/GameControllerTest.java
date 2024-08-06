package com.sas.wordgame.controller;

import com.sas.wordgame.service.WordGameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
                .andExpect(jsonPath("$.message").value("Congratulations! You've guessed the word."))
                .andExpect(jsonPath("$.attempts").value(1));
    }

    @Test
    public void testMakeGuessIncorrectPosition() throws Exception {
        String targetWord = wordGameService.getTargetWord();
        String guess = targetWord.substring(1) + targetWord.charAt(0);

        mockMvc.perform(post("/api/game/guess")
                        .param("guess", guess))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").exists())
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
    public void testMaxAttemptsExceeded() throws Exception {
        for (int i = 0; i < 6; i++) {
            mockMvc.perform(post("/api/game/guess")
                            .param("guess", "apple"))
                    .andExpect(status().isOk());
        }

        mockMvc.perform(post("/api/game/guess")
                        .param("guess", "apple"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").doesNotExist())
                .andExpect(jsonPath("$.message").value("You have exhausted all attempts!"));
    }
}
