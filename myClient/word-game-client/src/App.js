import React, { useState } from 'react';
import { Container } from '@mui/material';
import axios from 'axios';
import GuessInput from './components/GuessInput';
import ResultDisplay from './components/ResultDisplay';
import AttemptsDisplay from './components/AttemptsDisplay';
import MessageDisplay from './components/MessageDisplay';
import {Typography} from '@mui/material'

const App = () => {
  const [guess, setGuess] = useState('');
  const [result, setResult] = useState('');
  const [attempts, setAttempts] = useState(0);
  const [message, setMessage] = useState('');
  const [gameStatus, setGameStatus] = useState('playing'); // 'playing' or 'finished'

  const handleGuess = async () => {
    if (guess.length !== 5) {
      alert('Guess must be exactly 5 letters long!');
      return;
    }

    try {
      const response = await axios.post('http://localhost:8080/api/game/guess', null, { params: { guess } });
      
      const { data } = response;
      

      setResult(data.result);
      setAttempts(data.attempts);
      setMessage(data.message);

      if (data.message && data.message.includes("You have reached the maximum number of attempts")) {
        setGameStatus('finished');
      } else if (data.message && data.message.includes("Congratulations")) {
        setGameStatus('finished');
      }
    } catch (error) {
      console.error('Error making guess:', error);
    }
  };

  const handleNewGame = () => {
    setGameStatus('playing');
    setResult('');
    setMessage('');
    setAttempts(0);
    setGuess('');
  };

  return (
    <Container>
      <Typography variant="h4" gutterBottom>Word Guess Game</Typography>
      <GuessInput
        guess={guess}
        setGuess={setGuess}
        handleGuess={handleGuess}
        gameStatus={gameStatus}
      />
      <ResultDisplay result={result} />
      <AttemptsDisplay attempts={attempts} />
      <MessageDisplay message={message} handleNewGame={handleNewGame} gameStatus={gameStatus} />
    </Container>
  );
};

export default App;