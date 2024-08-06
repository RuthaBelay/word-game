# Word Guess Game

Welcome to the Word Guess Game! This is a web-based application where users can guess a five-letter word with limited attempts. The game provides feedback for each guess, indicating if a letter is in the correct position, in the word but incorrectly positioned, or not in the word at all.

## Table of Contents

- [Features](#features)
- [Technology Stack](#technology-stack)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Running the Application](#running-the-application)
- [Using Postman to Test the API](#using-postman-to-test-the-api)
- [Project Structure](#project-structure)

## Features

- **Interactive Gameplay**: Users can guess a five-letter word with feedback on each letter.
- **Multiple Attempts**: Users have six attempts to guess the word correctly.
- **User Feedback**: Letters are color-coded based on their correctness:
  - **Green (G)**: Correct position
  - **Yellow (Y)**: Incorrect position
  - **Grey (B)**: Not in word
- **Restart Option**: Start a new game after guessing correctly or reaching the maximum number of attempts.

## Technology Stack

- **Backend**: Java 17, Spring Boot, Spring MVC
- **Frontend**: React, Material-UI
- **Testing**: JUnit, Axios (for HTTP requests)
- **Server**: Embedded Tomcat
- **Build Tools**: Maven

## Prerequisites

Before you begin, ensure you have met the following requirements:

- **Java 11** or higher installed on your machine
- **Node.js** and **npm** installed for the frontend
- **IntelliJ IDEA** or another Java IDE for backend development
## Getting Started

### Backend Setup

1. Navigate to the backend directory:

    ```bash
    cd Server
    ```

2. Build the Spring Boot application:

    ```bash
    mvn clean install
    ```

3. Run the application:

    ```bash
    mvn spring-boot:run
    ```

   The backend server will start at [http://localhost:8080](http://localhost:8080).

### Frontend Setup

1. Navigate to the frontend directory:

    ```bash
    cd myClient
    ```

2. Install the dependencies:

    ```bash
    npm install
    ```

3. Start the React application:

    ```bash
    npm start
    ```

   The frontend server will start at [http://localhost:3000](http://localhost:3000).

## Running the Application

1. Open your browser and go to [http://localhost:3000](http://localhost:3000).
2. Enter a five-letter word as your guess.
3. Click the **Submit Guess** button to see the results.
4. Continue guessing until you either guess the word correctly or reach the maximum number of attempts.
5. Click the **Start New Game** button to play again.

## Using Postman to Test the API

You can use Postman or any API client to test the backend API directly. Here’s how:

### Test the Guess Endpoint

- **Endpoint**: `POST http://localhost:8080/api/game/guess`
- **Payload**:

    ```json
    {
      "guess": "apple"
    }
    ```

- **Response**:

    ```json
    {
      "result": "YGYGG",
      "message": "Congratulations! You've guessed the word. Starting a new game!",
      "attempts": 1
    }
    ```

### Test the Target Word Endpoint

- **Endpoint**: `GET http://localhost:8080/api/game/target-word`
- **Response**:

    ```json
    {
      "targetWord": "apple"
    }
    ```

## Project Structure

### Backend

```plaintext
wordGame-SAS/
├── Server/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/sas/wordgame/
│   │   │   │   ├── controller/
│   │   │   │   ├── service/
│   │   │   │   ├── model/
│   │   │   ├── resources/
│   │   │       ├── application.properties
│   │   └── test/
│   │       ├── java/com/sas/wordgame/
│   │           ├── WordGameServiceTest.java
├── Client/
│   ├── public/
│   ├── src/
│   │   ├── components/
│   │   ├── App.js
│   │   ├── index.js
│   ├── package.json
│   ├── package-lock.json
│   ├── README.md
Frontend
plaintext
Copy code
word-game-client/
└── Client/
    ├── public/
    ├── src/
    │   ├── components/
    │   │   ├── AttemptsDisplay.js
    │   │   ├── GuessInput.js
    │   │   ├── MessageDisplay.js
    │   │   ├── ResultDisplay.js
    │   ├── App.js
    │   ├── index.js
    ├── package.json
    ├── package-lock.json
