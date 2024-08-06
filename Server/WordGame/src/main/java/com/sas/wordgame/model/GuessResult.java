package com.sas.wordgame.model;

public class GuessResult {

    private String result;
    private String message;
    private Integer attempts;

    public GuessResult(String result, String message, Integer attempts) {
        this.result = result;
        this.message = message;
        this.attempts = attempts;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getAttempts() {
        return attempts;
    }

    public void setAttempts(Integer attempts) {
        this.attempts = attempts;
    }
}



